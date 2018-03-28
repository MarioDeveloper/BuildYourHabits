package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.dao.UserDAO;
import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.OneTimeEvent;
import com.everydayhabits.product.module.web.entity.RealizationRecurringEvent;
import com.everydayhabits.product.module.web.entity.RecurringEvent;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        user.setExperience(0);
        user.setLife(100);

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
    public void createRecurringEvent(RecurringEvent recurringEvent, String username) {
        userDAO.createRecurringEvent(recurringEvent, username);
    }

    @Override
    public void performRecurringEvent(int eventId) {
        userDAO.performRecurringEvent(eventId);
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
