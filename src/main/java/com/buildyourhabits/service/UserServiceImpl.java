package com.buildyourhabits.service;

import com.buildyourhabits.dto.ChallengeEventDto;
import com.buildyourhabits.dto.NotificationDto;
import com.buildyourhabits.dto.UserDto;
import com.buildyourhabits.dao.UserDAO;
import com.buildyourhabits.entity.OneTimeEvent;
import com.buildyourhabits.entity.RealizationRecurringEvent;
import com.buildyourhabits.entity.RecurringEvent;
import com.buildyourhabits.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User registerNewUserAccount(UserDto registerUser) {

        if(emailExist(registerUser.getEmail())) {
            return null;
        }

        User user = new User();
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setUsername(registerUser.getEmail());
        user.setCity(registerUser.getCity());
        user.setExperience(0);
        user.setLife(100);
        user.setRegistrationDate(new Date());

        if (registerUser.getGender().equals("Mężczyzna")) {
            user.setGender("M");
        } else {
            user.setGender("K");
        }

        user = userDAO.saveUser(user);

        return user;

    }

    public boolean emailExist(String email) {
        User user = userDAO.findByEmail(email);
        if(user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User validUserByUsername(String email) {

        Boolean isCreated = emailExist(email);

        User user = getUserByUsername(email);

        if (isCreated && !user.getUsername().equals(email)) {
            System.out.println("UserSrviceIMpl jestem w if");
            return null;
        }
        System.out.println("Zwracam usera");
        return new User();
    }

    @Override
    public void updateUserPersonalData(UserDto userDto) {
        userDAO.updateUserPersonalData(userDto);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByEmail(username);
    }

    @Override
    public List<OneTimeEvent> getOneTimeEventsByUserId(int userId) {
        return userDAO.getOneTimeEventsByUserId(userId);
    }

    @Override
    public List<RecurringEvent> getRecurringEventsByUserId(int userId) {
        return userDAO.getRecurringEventsByUserId(userId);
    }

    @Override
    public List<RealizationRecurringEvent> getRealizationRecurringEventList(List<RecurringEvent> recurringEventList) {
        return userDAO.getRealizationRecurringEventList(recurringEventList);
    }

    @Override
    public void createOneTimeEvent(OneTimeEvent ote, String username) {
        userDAO.createOneTimeEvent(ote, username);
    }

    @Override
    public void performOneTimeEvent(int theId) {
        userDAO.performOneTimeEvent(theId);
    }


    @Override
    public OneTimeEvent getOneTimeEventById(int eventId) {
        return userDAO.getOneTimeEventById(eventId);
    }

    @Override
    public RecurringEvent getRecurringEventById(int eventId) {
        return userDAO.getRecurringEventById(eventId);
    }

    @Override
    public void updateRecurringEvent(RecurringEvent recurringEvent) {
        userDAO.updateRecurringEvent(recurringEvent);
    }

    @Override
    public void failOneTimeEvent(int eventId, String username) {
        userDAO.failOneTimeEvent(eventId, username);
    }


    @Override
    public void updateOneTimeEvent(OneTimeEvent oneTimeEvent) {
        userDAO.updateOneTimeEvent(oneTimeEvent);
    }

    @Override
    public void deleteOneTimeEvent(int eventId) {
        userDAO.deleteOneTimeEvent(eventId);
    }

    @Override
    public List<RealizationRecurringEvent> getRealizationRecurringEventsByUserIdForHistory(List<RecurringEvent> recurringEventList) {
        return userDAO.getRealizationRecurringEventsByUserIdForHistory(recurringEventList);
    }

    @Override
    public List<RecurringEvent> getRecurringEventsByUserIdForHistory(int id) {
        return userDAO.getRecurringEventsByUserIdForHistory(id);
    }

    @Override
    public void createRecurringEvent(RecurringEvent recurringEvent, String username) {
        userDAO.createRecurringEvent(recurringEvent, username);
    }

    @Override
    public RealizationRecurringEvent getRealizationRecurringEventListByRealizationEventId(int eventId) {
        return userDAO.getRealizationRecurringEventListByRealizationEventId(eventId);
    }

    @Override
    public void performRecurringEvent(int eventId) {
        userDAO.performRecurringEvent(eventId);
    }

    @Override
    public void failRecurringEvent(int eventId, String username) {
        userDAO.failRecurringEvent(eventId, username);
    }

    @Override
    public void skipRecurringEvent(int eventId) {
        userDAO.skipRecurringEvent(eventId);
    }

    @Override
    public void cancelOtherRecurringEvents(int eventId) {
        userDAO.cancelOtherRecurringEvents(eventId);
    }

    @Override
    public List<User> getUsersByCriteria(String criteria, String username) {
        return userDAO.getUsersByCriteria(criteria, username);
    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
        return userDAO.getUserDtoByUsername(username);
    }

    @Override
    public List<NotificationDto> getNotifications(User user) {
        return userDAO.getNotifications(user);
    }

    @Override
    public List<User> getAllUsers(String username) {
        return userDAO.getAllUsers(username);
    }

    @Override
    public void createChallengeEvent(ChallengeEventDto challengeEventDto, String username) {
        userDAO.createChallengeEvent(challengeEventDto,username);
    }

    @Override
    public void rejectChallengeEvent(int theId) {
        userDAO.rejectChallengeEvent(theId);
    }

    @Override
    public void acceptChallengeEvent(int theId) {
        userDAO.acceptChallengeEvent(theId);
    }

    @Override
    public List<NotificationDto> getChallengeNotifications(User loggedUser) {
        return userDAO.getChallengeNotifications(loggedUser);
    }

    @Override
    public void failChallengeEvent(int theId, User loggedUser) {
        userDAO.failChallengeEvent(theId,loggedUser);
    }

    @Override
    public void performChallengeEvent(int theId, User loggedUser) {
        userDAO.performChallengeEvent(theId,loggedUser);
    }

    @Override
    public List<ChallengeEventDto> getChallengeEventsByUser(User user) {
       return userDAO.getChallengeEventsByUser(user);
    }

    @Override
    public List<OneTimeEvent> getOneTimeEventsByUserIdForHistory(int id) {
        return userDAO.getOneTimeEventsByUserIdForHistory(id);
    }

    @Override
    public void saveImage(MultipartFile file, String username) {
        userDAO.saveImage(file, username);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();

        authority.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authority);
    }
}
