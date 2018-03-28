package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.entity.*;

import java.util.List;

public interface UserDAO {

    public User saveUser(User theUser);

    User findByEmail(String email);

    User getUserByEmail(String theEmail);

    Level getLevelById(int levelId);

    List<OneTimeEvent> getOneTimeEventsByUserId(int userId);

    void performOneTimeEvent(int theId);

    OneTimeEvent getOneTimeEventById(int eventId);

    void createOneTimeEvent(OneTimeEvent ote, String username);

    void deleteOneTimeEvent(int eventId);

    void failOneTimeEvent(int eventId, String username);

    void updateOneTimeEvent(OneTimeEvent oneTimeEvent);

    void createRecurringEvent(RecurringEvent recurringEvent, String username);

    void performRecurringEvent(int eventId);

    List<RecurringEvent> getRecurringEventsByUserId(int userId);

    List<RealizationRecurringEvent> getRealizationRecurringEventList(List<RecurringEvent> recurringEventList);

    RecurringEvent getRecurringEventById(int eventId);

    void updateRecurringEvent(RecurringEvent recurringEvent);
}
