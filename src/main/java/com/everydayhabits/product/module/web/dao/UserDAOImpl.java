package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.dto.ChallengeEventDto;
import com.everydayhabits.product.module.web.dto.NotificationDto;
import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Query userQuery;
    private Session currentSession;

    @Override
    public User saveUser(User theUser) {

        currentSession = sessionFactory.getCurrentSession();

        theUser.setLevel(getLevelById(1));

        currentSession.save(theUser);

        String userEmail = theUser.getUsername();

        return getUserByEmail(userEmail);
    }

    @Override
    public User getUserByEmail(String email) {

        currentSession = sessionFactory.getCurrentSession();

        userQuery = currentSession.createQuery("from User where username=:userEmail");
        userQuery.setParameter("userEmail", email);

        User user = (User) userQuery.uniqueResult();

        return user;
    }

    @Override
    public List<User> getAllUsers(String username) {

        currentSession = sessionFactory.getCurrentSession();

        userQuery = currentSession.createQuery("from User WHERE username != :loggedUser ORDER BY firstName asc");
        userQuery.setParameter("loggedUser", username);

        List<User> users = userQuery.getResultList();

        return users;
    }

    @Override
    public void createChallengeEvent(ChallengeEventDto ced, String username) {

        currentSession = sessionFactory.getCurrentSession();

        User loggedUser = getUserByEmail(username);

        User userOpponent = getUserById(ced.getOpponentId());

        int experience = getExperienceDependentOnDifficultyLevel(null, null, ced);
        int life = getLifeDependentOnDifficultyLevel(null, null, ced);

        Notification tempNotification = new Notification(loggedUser.getFirstName(), loggedUser.getLastName(), 0, new Date(), "C", false);

        userOpponent.addNotification(tempNotification);

        currentSession.save(tempNotification);

        ChallengeEvent newChallengeEvent = new ChallengeEvent(loggedUser, tempNotification, ced.getTitle(), ced.getDescription(), ced.getDifficultyLevel(), null, null, null, ced.getPlannedDate(), null, experience, life);

        newChallengeEvent.addUser(loggedUser);
        newChallengeEvent.addUser(userOpponent);

        currentSession.save(newChallengeEvent);
    }

    private User getUserById(int opponentId) {

        currentSession = sessionFactory.getCurrentSession();

        userQuery = currentSession.createQuery("from User WHERE id = :userOpponentId");
        userQuery.setParameter("userOpponentId", opponentId);

        User user = (User) userQuery.uniqueResult();

        return user;

    }

    @Override
    public void performChallengeEvent(int theId, User loggedUser) {

        currentSession = sessionFactory.getCurrentSession();

        ChallengeEvent challengeEvent = currentSession.get(ChallengeEvent.class, theId);
        List<User> userList = challengeEvent.getUsers();

        for(User tempUser : userList) {
            if (challengeEvent.getUser().getId() == loggedUser.getId() && tempUser.getId() == loggedUser.getId()) {
                challengeEvent.setDoneUserInit(Boolean.TRUE);
                tempUser.setExperience(tempUser.getExperience() + challengeEvent.getExperience());

                checkUserExperiencePoint(tempUser);
            } else if(tempUser.getId() == loggedUser.getId()) {
                challengeEvent.setDoneUserOpponent(Boolean.TRUE);
                tempUser.setExperience(tempUser.getExperience() + challengeEvent.getExperience());

                checkUserExperiencePoint(tempUser);
            }
        }



    }

    @Override
    public void acceptChallengeEvent(int theId) {
        currentSession = sessionFactory.getCurrentSession();

        ChallengeEvent challengeEvent = currentSession.get(ChallengeEvent.class, theId);
        challengeEvent.setConfirmed(Boolean.TRUE);
    }

    @Override
    public List<NotificationDto> getChallengeNotifications(User loggedUser) {

        currentSession = sessionFactory.getCurrentSession();

        Query<Notification> theQuery = currentSession.createQuery("FROM Notification WHERE user_id != :userId AND type = 'C' ORDER BY date desc");
        theQuery.setParameter("userId", loggedUser.getId());
        theQuery.setMaxResults(5);

        List<Notification> notificationList = theQuery.getResultList();

        return getReturnedNotificationList(notificationList);

    }

    private List<NotificationDto> getReturnedNotificationList(List<Notification> notificationList) {
        List<NotificationDto> notificationDtos = new ArrayList<>();

        for (Notification notification : notificationList) {

            String tempDate = "";

            long diff = Math.abs(new Date().getTime() - notification.getDate().getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffHour = diff / (60 * 60 * 1000);
            long diffMinute = diff / (60 * 1000);

            if (diffDays > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(diffDays);
                sb.append(" Dni");
                tempDate = sb.toString();

            } else if (diffHour > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(diffHour);
                sb.append(" Godzin");
                tempDate = sb.toString();

            } else {

                if (diffMinute == 0) {
                    diffMinute = 1;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(diffMinute);
                sb.append(" Minut");
                tempDate = sb.toString();
            }
            NotificationDto tempNotification = new NotificationDto(notification.getFirstName(), notification.getLastName(), notification.getExperiencePoint(), tempDate);

            notificationDtos.add(tempNotification);
        }
        return notificationDtos;
    }

    @Override
    public List<ChallengeEventDto> getChallengeEventsByUser(User user) {

        currentSession = sessionFactory.getCurrentSession();


        Query queryChallenge = currentSession.createQuery("SELECT ce FROM ChallengeEvent ce JOIN ce.users ces where ces.id =:user_id AND (ce.isConfirmed != :code OR ce.isConfirmed IS NULL) ORDER BY ce.plannedDate asc");
        queryChallenge.setParameter("user_id", user.getId());
        queryChallenge.setParameter("code", Boolean.FALSE);

        List<ChallengeEventDto> challengeEvents = new ArrayList<>();
        List<ChallengeEvent> challengeEventList1 = queryChallenge.getResultList();


        // set opponent for challengeEvent
        int tempId;
        for(ChallengeEvent challengeEvent : challengeEventList1) {
            for (int i = 0; i < challengeEvent.getUsers().size(); i++) {
                 tempId = challengeEvent.getUsers().get(i).getId();
                if (!(user.getId() == tempId)) {
                    User tempUser = currentSession.get(User.class, tempId);
                    ChallengeEventDto challengeEventDto = new ChallengeEventDto(challengeEvent.getId(), challengeEvent.getTitle(), challengeEvent.getDescription(), challengeEvent.getDifficultyLevel(), challengeEvent.getPlannedDate(), tempUser.getFirstName(), tempUser.getLastName(), challengeEvent.getConfirmed(), challengeEvent.getUser().getId());

                    if(user.getId() == challengeEvent.getUser().getId() && challengeEvent.getDoneUserInit() == null)
                    challengeEvents.add(challengeEventDto);
                    else if(!(user.getId() == challengeEvent.getUser().getId()) && challengeEvent.getDoneUserOpponent() == null)
                        challengeEvents.add(challengeEventDto);
                }
            }
        }

        return challengeEvents;
    }

    @Override
    public void rejectChallengeEvent(int theId) {
        currentSession = sessionFactory.getCurrentSession();

        ChallengeEvent challengeEvent = currentSession.get(ChallengeEvent.class, theId);
        challengeEvent.setConfirmed(Boolean.FALSE);
    }

    @Override
    public UserDto getUserDtoByUsername(String username) {

        User user = getUserByEmail(username);

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setMatchingPassword(user.getPassword());
        userDto.setGender(user.getGender());
        return userDto;
    }

    @Override
    public User findByEmail(String email) {

        return getUserByEmail(email);
    }


    @Override
    public Level getLevelById(int levelId) {

        currentSession = sessionFactory.getCurrentSession();

        Level theLevel = currentSession.get(Level.class, levelId);

        return theLevel;
    }

    @Override
    public List<OneTimeEvent> getOneTimeEventsByUserId(int userId) {

        currentSession = sessionFactory.getCurrentSession();

        Query<OneTimeEvent> theQuery = currentSession.createQuery(" FROM OneTimeEvent WHERE user.id = :user_id AND realizationDate IS NULL AND is_done IS NULL ORDER BY plannedDate asc");
        theQuery.setParameter("user_id", userId);

        List<OneTimeEvent> oneTimeEventList = theQuery.getResultList();

        return oneTimeEventList;
    }

    @Override
    public List<OneTimeEvent> getOneTimeEventsByUserIdForHistory(int id) {

        currentSession = sessionFactory.getCurrentSession();

        Query<OneTimeEvent> theQuery = currentSession.createQuery(" FROM OneTimeEvent WHERE user.id = :user_id  AND is_done IS NOT NULL ORDER BY realizationDate asc");
        theQuery.setParameter("user_id", id);

        List<OneTimeEvent> oneTimeEventList = theQuery.getResultList();

        return oneTimeEventList;
    }


    @Override
    public List<RecurringEvent> getRecurringEventsByUserId(int userId) {

        currentSession = sessionFactory.getCurrentSession();

        Query<RecurringEvent> recurringEventQuery = currentSession.createQuery(" FROM RecurringEvent WHERE user.id =:user_id AND is_done IS NULL ORDER BY startDate asc");
        recurringEventQuery.setParameter("user_id", userId);

        List<RecurringEvent> recurringEventList = recurringEventQuery.getResultList();

        return recurringEventList;

    }


    @Override
    public List<RecurringEvent> getRecurringEventsByUserIdForHistory(int id) {
        currentSession = sessionFactory.getCurrentSession();

        Query<RecurringEvent> recurringEventQuery = currentSession.createQuery(" FROM RecurringEvent WHERE user.id =:user_id ORDER BY startDate asc");
        recurringEventQuery.setParameter("user_id", id);

        List<RecurringEvent> recurringEventList = recurringEventQuery.getResultList();

        return recurringEventList;
    }

    @Override
    public List<RealizationRecurringEvent> getRealizationRecurringEventsByUserIdForHistory(List<RecurringEvent> recurringEventList) {

        currentSession = sessionFactory.getCurrentSession();

        ArrayList<RealizationRecurringEvent> realizationRecurringEventList = new ArrayList<>();

        for (RecurringEvent recurringEvent : recurringEventList) {

            Query<RealizationRecurringEvent> theQuery = currentSession.createQuery(" FROM RealizationRecurringEvent WHERE recurringEvent.id =:recurring_event_id AND is_done IS NOT NULL ORDER BY realizationDate asc");
            theQuery.setParameter("recurring_event_id", recurringEvent.getId());

            List<RealizationRecurringEvent> tempRealization = theQuery.getResultList();

            realizationRecurringEventList.addAll(tempRealization);

        }
        return realizationRecurringEventList;
    }

    @Override
    public List<RealizationRecurringEvent> getRealizationRecurringEventList(List<RecurringEvent> recurringEventList) {

        currentSession = sessionFactory.getCurrentSession();

        ArrayList<RealizationRecurringEvent> realizationRecurringEventList = new ArrayList<>();

        for (RecurringEvent recurringEvent : recurringEventList) {

            Query<RealizationRecurringEvent> theQuery = currentSession.createQuery(" FROM RealizationRecurringEvent WHERE recurringEvent.id =:recurring_event_id AND is_done IS NULL");
            theQuery.setParameter("recurring_event_id", recurringEvent.getId());

            RealizationRecurringEvent tempRealization = theQuery.uniqueResult();
            realizationRecurringEventList.add(tempRealization);
        }
        return realizationRecurringEventList;
    }

    @Override
    public void failChallengeEvent(int theId, User loggedUser) {

        currentSession = sessionFactory.getCurrentSession();

        ChallengeEvent challengeEvent = currentSession.get(ChallengeEvent.class, theId);
        List<User> userList = challengeEvent.getUsers();

        for(User tempUser : userList) {
            if (challengeEvent.getUser().getId() == loggedUser.getId() && tempUser.getId() == loggedUser.getId()) {
                challengeEvent.setDoneUserInit(Boolean.FALSE);
                tempUser.setLife(tempUser.getLife() + challengeEvent.getLife());
                checkUserLife(tempUser);

            } else if(tempUser.getId() == loggedUser.getId()) {
                challengeEvent.setDoneUserOpponent(Boolean.FALSE);
                tempUser.setLife(tempUser.getLife() + challengeEvent.getLife());
                checkUserLife(tempUser);
            }
        }
    }

    @Override
    public void failOneTimeEvent(int eventId, String username) {

        currentSession = sessionFactory.getCurrentSession();

        OneTimeEvent tempOneTimeEvent = currentSession.get(OneTimeEvent.class, eventId);

        User tempUser = currentSession.get(User.class, tempOneTimeEvent.getUser().getId());

        Query updateUser = currentSession.createQuery(" UPDATE User SET life =:life_point WHERE id = :user_id");
        updateUser.setParameter("life_point", (tempUser.getLife() + tempOneTimeEvent.getLife()));
        updateUser.setParameter("user_id", tempUser.getId());
        updateUser.executeUpdate();

        // refresh user
        currentSession.refresh(tempUser);
        checkUserLife(tempUser);

        Query updateFailedTaskQuery = currentSession.createQuery("UPDATE OneTimeEvent SET isDone=:code WHERE id=:event_Id");
        updateFailedTaskQuery.setParameter("code", Boolean.FALSE);
        updateFailedTaskQuery.setParameter("event_Id", eventId);
        updateFailedTaskQuery.executeUpdate();

    }

    @Override
    public void deleteOneTimeEvent(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        Query deleteEventQuery = currentSession.createQuery("DELETE OneTimeEvent WHERE id= :event_Id");
        deleteEventQuery.setParameter("event_Id", eventId);
        deleteEventQuery.executeUpdate();
    }

    @Override
    public void performOneTimeEvent(int theId) {

        currentSession = sessionFactory.getCurrentSession();

        OneTimeEvent oneTimeEvent = currentSession.get(OneTimeEvent.class, theId);

        User user = currentSession.get(User.class, oneTimeEvent.getUser().getId());

        Query updateUserQuery = currentSession.createQuery("UPDATE User SET experience=:experiencePoint WHERE id=:userId");
        updateUserQuery.setParameter("experiencePoint", oneTimeEvent.getExperience() + user.getExperience());
        updateUserQuery.setParameter("userId", oneTimeEvent.getUser().getId());
        updateUserQuery.executeUpdate();

        Query updateEventQuery = currentSession.createQuery("UPDATE OneTimeEvent SET realizationDate=:currentDate, isDone=:code WHERE id=:eventId");
        updateEventQuery.setParameter("currentDate", new Date());
        updateEventQuery.setParameter("code", Boolean.TRUE);
        updateEventQuery.setParameter("eventId", theId);
        updateEventQuery.executeUpdate();

        currentSession.refresh(user);
        checkUserExperiencePoint(user);

        Notification notification = new Notification(user.getFirstName(), user.getLastName(), oneTimeEvent.getExperience(), new Date(), "O", null);

        user.addNotification(notification);

        currentSession.save(notification);

    }

    @Override
    public void createOneTimeEvent(OneTimeEvent ote, String username) {

        currentSession = sessionFactory.getCurrentSession();

        int experience = getExperienceDependentOnDifficultyLevel(ote, null, null);
        int life = getLifeDependentOnDifficultyLevel(ote, null,null);

        User user = getUserByEmail(username);

        OneTimeEvent newOneTimeEvent = new OneTimeEvent(ote.getTitle(), ote.getDescription(), ote.getDifficultyLevel(), ote.getPlannedDate(), experience, life, null);

        user.addOneTimeEvent(newOneTimeEvent);

        currentSession.save(newOneTimeEvent);
    }

    @Override
    public void updateOneTimeEvent(OneTimeEvent oneTimeEvent) {

        currentSession = sessionFactory.getCurrentSession();

        OneTimeEvent oneTimeEvent1 = currentSession.get(OneTimeEvent.class, oneTimeEvent.getId());

        oneTimeEvent1.setTitle(oneTimeEvent.getTitle());
        oneTimeEvent1.setDescription(oneTimeEvent.getDescription());
        oneTimeEvent1.setDifficultyLevel(oneTimeEvent.getDifficultyLevel());
        oneTimeEvent1.setPlannedDate(oneTimeEvent.getPlannedDate());
    }

    @Override
    public OneTimeEvent getOneTimeEventById(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        OneTimeEvent oneTimeEvent = currentSession.get(OneTimeEvent.class, eventId);

        return oneTimeEvent;
    }

    @Override
    public RecurringEvent getRecurringEventById(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent = currentSession.get(RecurringEvent.class, eventId);

        return recurringEvent;
    }

    @Override
    public void createRecurringEvent(RecurringEvent recurringEvent, String username) {

        currentSession = sessionFactory.getCurrentSession();

        int experience = getExperienceDependentOnDifficultyLevel(null, recurringEvent, null);
        int life = getLifeDependentOnDifficultyLevel(null, recurringEvent, null);

        User user = getUserByEmail(username);

        RecurringEvent tempRecurringEvent = new RecurringEvent(recurringEvent.getTitle(), recurringEvent.getDescription(), recurringEvent.getDifficultyLevel(), recurringEvent.getFrequency(), recurringEvent.getFrequencyUnit(), recurringEvent.getStartDate(), recurringEvent.getFinishDate(), null);

        RealizationRecurringEvent realizationRecurringEvent = new RealizationRecurringEvent(recurringEvent.getStartDate(), experience, life, null);

        user.addRecuuringEvent(tempRecurringEvent);
        tempRecurringEvent.addRealizationRecurringEvent(realizationRecurringEvent);

        currentSession.save(tempRecurringEvent);

    }

    @Override
    public void performRecurringEvent(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent = currentSession.get(RecurringEvent.class, eventId);

        User user = currentSession.get(User.class, recurringEvent.getUser().getId());

        Query realizationEventQuery = currentSession.createQuery("FROM RealizationRecurringEvent WHERE recurringEvent.id =:event_ID AND isDone IS NULL");
        realizationEventQuery.setParameter("event_ID", eventId);

        RealizationRecurringEvent realizationRecurringEvent = (RealizationRecurringEvent) realizationEventQuery.uniqueResult();

        Query updateUserQuery = currentSession.createQuery("UPDATE User SET experience=:experiencePoint WHERE id=:userId");
        updateUserQuery.setParameter("experiencePoint", realizationRecurringEvent.getExperience() + user.getExperience());
        updateUserQuery.setParameter("userId", recurringEvent.getUser().getId());
        updateUserQuery.executeUpdate();

        Query updateEventQuery = currentSession.createQuery("UPDATE RealizationRecurringEvent SET realizationDate=:currentDate, isDone=:code WHERE id=:eventId");
        updateEventQuery.setParameter("currentDate", new Date());
        updateEventQuery.setParameter("code", Boolean.TRUE);
        updateEventQuery.setParameter("eventId", realizationRecurringEvent.getId());
        updateEventQuery.executeUpdate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(realizationRecurringEvent.getPlannedDate());

        Date tempDate = updateDateUsingFrequencyAndFrequencyUnit(recurringEvent, calendar);

        checkDateForCreationNewRealizationReccuringEvent(eventId, recurringEvent, realizationRecurringEvent, tempDate);

        currentSession.refresh(user);
        checkUserExperiencePoint(user);
    }

    private void checkDateForCreationNewRealizationReccuringEvent(int eventId, RecurringEvent recurringEvent, RealizationRecurringEvent realizationRecurringEvent, Date tempDate) {
        if (tempDate.before(recurringEvent.getFinishDate())) {
            RealizationRecurringEvent realizationRecurringEvent1 = new RealizationRecurringEvent(tempDate, realizationRecurringEvent.getExperience(), realizationRecurringEvent.getLife(), null);
            recurringEvent.addRealizationRecurringEvent(realizationRecurringEvent1);
            currentSession.save(realizationRecurringEvent1);
        } else {
            Query updateQuery = currentSession.createQuery("UPDATE RecurringEvent SET isDone=:code WHERE id=:event_Id");
            updateQuery.setParameter("code", Boolean.TRUE);
            updateQuery.setParameter("event_Id", eventId);
            updateQuery.executeUpdate();
        }
    }


    @Override
    public void updateRecurringEvent(RecurringEvent recurringEvent) {

        currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent1 = currentSession.get(RecurringEvent.class, recurringEvent.getId());

        recurringEvent1.setTitle(recurringEvent.getTitle());
        recurringEvent1.setDescription(recurringEvent.getDescription());
        recurringEvent1.setDifficultyLevel(recurringEvent.getDifficultyLevel());
        recurringEvent1.setFinishDate(recurringEvent.getFinishDate());
        recurringEvent1.setFrequency(recurringEvent.getFrequency());
        recurringEvent1.setFrequencyUnit(recurringEvent.getFrequencyUnit());

        Query realizationEventQuery = currentSession.createQuery("FROM RealizationRecurringEvent WHERE recurringEvent =:event_ID AND isDone IS NULL");
        realizationEventQuery.setParameter("event_ID", recurringEvent.getId());

        RealizationRecurringEvent realizationRecurringEvent = (RealizationRecurringEvent) realizationEventQuery.uniqueResult();

        Query updateRealizationQuery = currentSession.createQuery("UPDATE RealizationRecurringEvent SET plannedDate =:event_planned_date WHERE id =:realization_id");
        updateRealizationQuery.setParameter("event_planned_date", recurringEvent.getStartDate());
        updateRealizationQuery.setParameter("realization_id", realizationRecurringEvent.getId());
        updateRealizationQuery.executeUpdate();
    }

    @Override
    public void updateUserPersonalData(UserDto userDto) {

        currentSession = sessionFactory.getCurrentSession();

        User user = currentSession.get(User.class, userDto.getId());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getEmail());
//        user.setImage(userDto.getImage());
        user.setCity(userDto.getCity());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    }

    @Override
    public void failRecurringEvent(int eventId, String username) {

        currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent = currentSession.get(RecurringEvent.class, eventId);

        User tempUser = currentSession.get(User.class, recurringEvent.getUser().getId());

        Query realizationRecurringEventQuery = currentSession.createQuery("FROM RealizationRecurringEvent WHERE recurringEvent.id =:event_id AND isDone IS NULL");
        realizationRecurringEventQuery.setParameter("event_id", recurringEvent.getId());

        RealizationRecurringEvent realizationRecurringEvent = (RealizationRecurringEvent) realizationRecurringEventQuery.uniqueResult();

        Query updateQuery = currentSession.createQuery("UPDATE RealizationRecurringEvent SET isDone=:code WHERE id=:event_Id");
        updateQuery.setParameter("code", Boolean.FALSE);
        updateQuery.setParameter("event_Id", realizationRecurringEvent.getId());
        updateQuery.executeUpdate();

        Query updateUser = currentSession.createQuery("UPDATE User SET life =:life_point WHERE id = :user_id ");
        updateUser.setParameter("life_point", (tempUser.getLife() + realizationRecurringEvent.getLife()));
        updateUser.setParameter("user_id", tempUser.getId());
        updateUser.executeUpdate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(realizationRecurringEvent.getPlannedDate());

        Date tempDate = updateDateUsingFrequencyAndFrequencyUnit(recurringEvent, calendar);

        checkDateForCreationNewRealizationReccuringEvent(eventId, recurringEvent, realizationRecurringEvent, tempDate);

        currentSession.refresh(tempUser);
        checkUserLife(tempUser);

    }

    @Override
    public void skipRecurringEvent(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent = currentSession.get(RecurringEvent.class, eventId);

        Query realizationRecurringEventQuery = currentSession.createQuery("FROM RealizationRecurringEvent WHERE recurringEvent.id =:event_id AND isDone IS NULL");
        realizationRecurringEventQuery.setParameter("event_id", recurringEvent.getId());

        RealizationRecurringEvent realizationRecurringEvent = (RealizationRecurringEvent) realizationRecurringEventQuery.uniqueResult();

        Query deleteQuery = currentSession.createQuery("DELETE RealizationRecurringEvent WHERE id =:event_Id");
        deleteQuery.setParameter("event_Id", realizationRecurringEvent.getId());
        deleteQuery.executeUpdate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(realizationRecurringEvent.getPlannedDate());

        Date tempDate = updateDateUsingFrequencyAndFrequencyUnit(recurringEvent, calendar);

        checkDateForCreationNewRealizationReccuringEvent(eventId, recurringEvent, realizationRecurringEvent, tempDate);

    }

    @Override
    public void cancelOtherRecurringEvents(int eventId) {

        currentSession = sessionFactory.getCurrentSession();

        Query deleteQuery = currentSession.createQuery("DELETE RealizationRecurringEvent WHERE recurringEvent.id =:event_Id AND isDone IS NULL");
        deleteQuery.setParameter("event_Id", eventId);
        deleteQuery.executeUpdate();

        Query updateQuery = currentSession.createQuery("UPDATE RecurringEvent SET isDone=:code WHERE id=:event_Id");
        updateQuery.setParameter("code", Boolean.FALSE);
        updateQuery.setParameter("event_Id", eventId);
        updateQuery.executeUpdate();
    }

    @Override
    public List<User> getUsersByCriteria(String criteria, String username) {

        currentSession = sessionFactory.getCurrentSession();

        List<User> userList = new ArrayList<>();

        User user = getUserByEmail(username);

        if (criteria.equals("allUsers")) {
            userQuery = currentSession.createQuery("FROM User ORDER BY level desc, experience desc");
            userList = userQuery.getResultList();
        } else if (criteria.equals("M")) {
            userQuery = currentSession.createQuery("FROM User WHERE gender =:userGender ORDER BY level desc, experience desc");
            userQuery.setParameter("userGender", criteria);
            userList = userQuery.getResultList();
        } else if (criteria.equals("K")) {
            userQuery = currentSession.createQuery("FROM User WHERE gender =:userGender ORDER BY level desc, experience desc");
            userQuery.setParameter("userGender", criteria);
            userList = userQuery.getResultList();
        } else {
            userQuery = currentSession.createQuery("FROM User WHERE city =:userCity ORDER BY level desc, experience desc");
            userQuery.setParameter("userCity", user.getCity());
            userList = userQuery.getResultList();
        }
        return userList;
    }


    @Override
    public void saveImage(MultipartFile file, String username) {

        User user = getUserByEmail(username);

        if (file != null && file.getSize() > 0) {
            try {
                user.setImage(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentSession.update(user);
        }


    }

    @Override
    public List<NotificationDto> getNotifications(User user) {

        currentSession = sessionFactory.getCurrentSession();

        Query<Notification> theQuery = currentSession.createQuery(" FROM Notification WHERE user_id !=:userId AND type = 'O' ORDER BY date desc");
        theQuery.setParameter("userId", user.getId());
        theQuery.setMaxResults(10);

        List<Notification> notificationList = theQuery.getResultList();

        return getReturnedNotificationList(notificationList);

    }

    @Override
    public void checkUserExperiencePoint(User user) {

        currentSession = sessionFactory.getCurrentSession();

        if (user.getExperience() >= 100) {
            Query updateUser = currentSession.createQuery("UPDATE User SET experience=:new_value, level_id=:user_level, life=:user_life WHERE id=:user_id");
            updateUser.setParameter("new_value", (user.getExperience() - 100));
            updateUser.setParameter("user_level", (user.getLevel().getId() + 1));
            updateUser.setParameter("user_life", 100);
            updateUser.setParameter("user_id", user.getId());
            updateUser.executeUpdate();
        }
    }

    @Override
    public void checkUserLife(User user) {

        currentSession = sessionFactory.getCurrentSession();


        if (user.getLife() < 0) {
            Query updateUser = currentSession.createQuery("UPDATE User SET experience=:new_value, level_id=:user_level, life=:user_life WHERE id=:user_id");
            updateUser.setParameter("new_value", 0);
            updateUser.setParameter("user_level", (user.getLevel().getId() - 1));
            updateUser.setParameter("user_life", 100);
            updateUser.setParameter("user_id", user.getId());
            updateUser.executeUpdate();
        }
    }

    // temporary ugly code
    private int getExperienceDependentOnDifficultyLevel(OneTimeEvent ote, RecurringEvent re, ChallengeEventDto ce) {

        int tempExperience = 0;

        if (ote != null) {

            if (ote.getDifficultyLevel().equals("Łatwy")) {
                tempExperience = 10;
            } else if (ote.getDifficultyLevel().equals("Średni")) {
                tempExperience = 20;
            } else {
                tempExperience = 25;
            }
        } else if(re != null ){
            if (re.getDifficultyLevel().equals("Łatwy")) {
                System.out.println();
                tempExperience = 10;
            } else if (ote.getDifficultyLevel().equals("Średni")) {
                tempExperience = 20;
            } else {
                tempExperience = 25;
            }
        } else {
            if (ce.getDifficultyLevel().equals("Łatwy")) {
                System.out.println("");
                tempExperience = 10;
            } else if (ce.getDifficultyLevel().equals("Średni")) {
                tempExperience = 20;
            } else {
                tempExperience = 25;
            }

        }
        return tempExperience;

    }

    // temporary ugly code
    private int getLifeDependentOnDifficultyLevel(OneTimeEvent ote, RecurringEvent re, ChallengeEventDto ce) {

        int tempLife = 0;

        if (ote != null) {

            if (ote.getDifficultyLevel().equals("Łatwy")) {
                tempLife = -10;
            } else if (ote.getDifficultyLevel().equals("Średni")) {
                tempLife = -10;
            } else {
                tempLife = -10;
            }
        } else if(re != null) {
            if (re.getDifficultyLevel().equals("Łatwy")) {
                System.out.println();
                tempLife = -10;
            } else if (ote.getDifficultyLevel().equals("Średni")) {
                tempLife = -10;
            } else {
                tempLife = -10;
            }
        } else {
            if (ce.getDifficultyLevel().equals("Łatwy")) {
                System.out.println("");
                tempLife = -10;
            } else if (ce.getDifficultyLevel().equals("Średni")) {
                tempLife = -10;
            } else {
                tempLife = -10;
            }
        }

        return tempLife;
    }

    private Date updateDateUsingFrequencyAndFrequencyUnit(RecurringEvent recurringEvent, Calendar calendar) {
        switch (recurringEvent.getFrequencyUnit()) {
            case "Minut":
                calendar.add(Calendar.MINUTE, recurringEvent.getFrequency());
                break;

            case "Godzin":
                calendar.add(Calendar.HOUR, recurringEvent.getFrequency());
                break;

            case "Dni":
                calendar.add(Calendar.DATE, recurringEvent.getFrequency());
                break;

            case "Tygodnie":
                calendar.add(Calendar.DAY_OF_MONTH, (recurringEvent.getFrequency() * 7));
                break;

            case "Miesiące":
                calendar.add(Calendar.MONTH, (recurringEvent.getFrequency()));
                break;
        }

        return calendar.getTime();
    }
}