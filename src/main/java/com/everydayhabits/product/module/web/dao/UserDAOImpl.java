package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User saveUser(User theUser) {

        Session currentSession = sessionFactory.getCurrentSession();

        theUser.setLevel(getLevelById(1));

        currentSession.save(theUser);

        String userEmail = theUser.getUsername();

        return getUserByEmail(userEmail);
    }

    @Override
    public User getUserByEmail(String email) {

        Session currentSession = sessionFactory.getCurrentSession();

        // get the customer by primary keyx
        Query theQuery = currentSession.createQuery("from User where username=:userEmail");
        theQuery.setParameter("userEmail", email);

        // execute query and get result list
        User user = (User) theQuery.uniqueResult();

        return user;
    }

    @Override
    public User findByEmail(String email) {

        return getUserByEmail(email);
    }


    @Override
    public Level getLevelById(int levelId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Level theLevel = currentSession.get(Level.class, levelId);

        return theLevel;
    }

    @Override
    public List<OneTimeEvent> getOneTimeEventsByUserId(int userId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<OneTimeEvent> theQuery = currentSession.createQuery(" FROM OneTimeEvent WHERE user.id = :user_id AND realizationDate IS NULL AND is_done IS NULL ORDER BY plannedDate asc");
        theQuery.setParameter("user_id", userId);

        List<OneTimeEvent> oneTimeEventList = theQuery.getResultList();

        System.out.println("Rozmiar listy: " + oneTimeEventList.size());

        return oneTimeEventList;
    }

    @Override
    public List<RecurringEvent> getRecurringEventsByUserId(int userId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query<RecurringEvent> theQuery = currentSession.createQuery(" FROM RecurringEvent WHERE user.id = :user_id AND is_done IS NULL ORDER BY startDate asc");
        theQuery.setParameter("user_id", userId);

        List<RecurringEvent> recurringEventList = theQuery.getResultList();

        System.out.println("Rozmiar listy: " + recurringEventList.size());

        return recurringEventList;

    }

    @Override
    public List<RealizationRecurringEvent> getRealizationRecurringEventList(List<RecurringEvent> recurringEventList) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

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
    public void failOneTimeEvent(int eventId, String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        // get oneTimeEvent
        OneTimeEvent tempOneTimeEvent = currentSession.get(OneTimeEvent.class, eventId);

        // get user
        User tempUser = currentSession.get(User.class, tempOneTimeEvent.getUser().getId());

        // update User table
        Query updateUser = currentSession.createQuery(" UPDATE User SET life =:life_point WHERE id = :user_id");
        updateUser.setParameter("life_point", (tempUser.getLife() + tempOneTimeEvent.getLife()));
        updateUser.setParameter("user_id", tempUser.getId());
        updateUser.executeUpdate();

        System.out.println("Wynik odejmowania" + (tempUser.getLife() + tempOneTimeEvent.getLife()));


        // update oneTimeEvent table
        Query updateFailedTaskQuery = currentSession.createQuery("UPDATE OneTimeEvent SET isDone=:code WHERE id=:event_Id");
        updateFailedTaskQuery.setParameter("code", Boolean.FALSE);
        updateFailedTaskQuery.setParameter("event_Id", eventId);
        updateFailedTaskQuery.executeUpdate();

    }

    @Override
    public void deleteOneTimeEvent(int eventId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query deleteEventQuery = currentSession.createQuery("DELETE OneTimeEvent WHERE id= :event_Id");
        deleteEventQuery.setParameter("event_Id", eventId);

        deleteEventQuery.executeUpdate();


    }

    @Override
    public void performOneTimeEvent(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

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


    }

    @Override
    public void createOneTimeEvent(OneTimeEvent ote, String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        int tempExperience = 0;
        int tempLife = 0;

        if (ote.getDifficultyLevel().equals("Łatwy")) {
            tempExperience = 10;
            tempLife = -10;
        } else if (ote.getDifficultyLevel().equals("Średni")) {
            tempExperience = 20;
            tempLife = -10;
        } else {
            tempExperience = 25;
            tempLife = -10;
        }

        User user = getUserByEmail(username);

        OneTimeEvent newOneTimeEvent = new OneTimeEvent(ote.getTitle(), ote.getDescription(), ote.getDifficultyLevel(), ote.getPlannedDate(), tempExperience, tempLife, null);

        System.out.println("Tytuł: " + ote.getTitle());

        user.addOneTimeEvent(newOneTimeEvent);

        currentSession.save(newOneTimeEvent);
    }

    @Override
    public void updateOneTimeEvent(OneTimeEvent oneTimeEvent) {
        Session currentSession = sessionFactory.getCurrentSession();

        System.out.println("Jakie Id: " + oneTimeEvent.getId());

        OneTimeEvent oneTimeEvent1 = currentSession.get(OneTimeEvent.class, oneTimeEvent.getId());

        oneTimeEvent1.setTitle(oneTimeEvent.getTitle());
        oneTimeEvent1.setDescription(oneTimeEvent.getDescription());
        oneTimeEvent1.setDifficultyLevel(oneTimeEvent.getDifficultyLevel());
        oneTimeEvent1.setPlannedDate(oneTimeEvent.getPlannedDate());
    }

    @Override
    public OneTimeEvent getOneTimeEventById(int eventId) {
        Session currentSession = sessionFactory.getCurrentSession();

        OneTimeEvent oneTimeEvent = currentSession.get(OneTimeEvent.class, eventId);

        return oneTimeEvent;
    }

    @Override
    public RecurringEvent getRecurringEventById(int eventId) {

        Session currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent = currentSession.get(RecurringEvent.class, eventId);

        return recurringEvent;
    }

    @Override
    public void createRecurringEvent(RecurringEvent recurringEvent, String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        int tempExperience = 0;
        int tempLife = 0;

        if (recurringEvent.getDifficultyLevel().equals("Łatwy")) {
            tempExperience = 10;
            tempLife = -10;
        } else if (recurringEvent.getDifficultyLevel().equals("Średni")) {
            tempExperience = 20;
            tempLife = -10;
        } else {
            System.out.println();
            tempExperience = 25;
            tempLife = -10;
        }

        User user = getUserByEmail(username);

        RecurringEvent tempRecurringEvent = new RecurringEvent(recurringEvent.getTitle(), recurringEvent.getDescription(), recurringEvent.getDifficultyLevel(), recurringEvent.getFrequency(), recurringEvent.getFrequencyUnit(), recurringEvent.getStartDate(), recurringEvent.getFinishDate(), null);

        RealizationRecurringEvent realizationRecurringEvent = new RealizationRecurringEvent(recurringEvent.getStartDate(), tempExperience, tempLife, null);

        user.addRecuuringEvent(tempRecurringEvent);

        tempRecurringEvent.addRealizationRecurringEvent(realizationRecurringEvent);

        currentSession.save(tempRecurringEvent);

    }

    @Override
    public void performRecurringEvent(int eventId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

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

        Date tempDate = calendar.getTime();

        if (tempDate.before(recurringEvent.getFinishDate())) {
            RealizationRecurringEvent realizationRecurringEvent1 = new RealizationRecurringEvent(tempDate, realizationRecurringEvent.getExperience(), realizationRecurringEvent.getLife(), null);
            recurringEvent.addRealizationRecurringEvent(realizationRecurringEvent1);
            currentSession.save(realizationRecurringEvent1);
        }

        // dokończyć, myślę, że update na recurringEvent is_done = 1
    }

    @Override
    public void updateRecurringEvent(RecurringEvent recurringEvent) {

        Session currentSession = sessionFactory.getCurrentSession();

        RecurringEvent recurringEvent1 = currentSession.get(RecurringEvent.class, recurringEvent.getId());

        recurringEvent1.setTitle(recurringEvent.getTitle());
        recurringEvent1.setDescription(recurringEvent.getDescription());
        recurringEvent1.setDifficultyLevel(recurringEvent.getDifficultyLevel());
        recurringEvent1.setFinishDate(recurringEvent.getFinishDate());
        recurringEvent1.setFrequency(recurringEvent.getFrequency());
        recurringEvent1.setFrequencyUnit(recurringEvent.getFrequencyUnit());

        Query realizationEventQuery = currentSession.createQuery("FROM RealizationRecurringEvent WHERE recurringEvent.id =:event_ID AND isDone IS NULL");
        realizationEventQuery.setParameter("event_ID", recurringEvent.getId());

        RealizationRecurringEvent realizationRecurringEvent = (RealizationRecurringEvent) realizationEventQuery.uniqueResult();

        Query updateRealizationQuery = currentSession.createQuery("UPDATE RealizationRecurringEvent SET plannedDate =:event_planned_date WHERE id =:realization_id");
        updateRealizationQuery.setParameter("event_planned_date", recurringEvent.getStartDate());
        updateRealizationQuery.setParameter("realization_id", realizationRecurringEvent.getId());
        updateRealizationQuery.executeUpdate();


    }
}
