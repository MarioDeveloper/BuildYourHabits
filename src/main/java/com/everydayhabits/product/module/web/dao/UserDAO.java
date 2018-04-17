package com.everydayhabits.product.module.web.dao;

import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.*;
import org.springframework.web.multipart.MultipartFile;

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

    void failRecurringEvent(int eventId, String username);

    void skipRecurringEvent(int eventId);

    void cancelOtherRecurringEvents(int eventId);

    List<User> getUsersByCriteria(String criteria, String username);

    List<Notification> getNotifications();

    UserDto getUserDtoByUsername(String username);

    void updateUserPersonalData(UserDto userDto);

    List<OneTimeEvent> getOneTimeEventsByUserIdForHistory(int id);

    List<RealizationRecurringEvent> getRealizationRecurringEventsByUserIdForHistory(List<RecurringEvent> recurringEventList);

    List<RecurringEvent> getRecurringEventsByUserIdForHistory(int id);

    void saveImage(MultipartFile file, String username);

    public void checkUserExperiencePoint(User user);

    public void checkUserLife(User user);
}
