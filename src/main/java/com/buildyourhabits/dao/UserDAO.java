package com.buildyourhabits.dao;

import com.buildyourhabits.dto.ChallengeEventDto;
import com.buildyourhabits.dto.NotificationDto;
import com.buildyourhabits.dto.UserDto;
import com.buildyourhabits.entity.*;
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

    List<NotificationDto> getNotifications(User user);

    UserDto getUserDtoByUsername(String username);

    void updateUserPersonalData(UserDto userDto);

    List<OneTimeEvent> getOneTimeEventsByUserIdForHistory(int id);

    List<RealizationRecurringEvent> getRealizationRecurringEventsByUserIdForHistory(List<RecurringEvent> recurringEventList);

    List<RecurringEvent> getRecurringEventsByUserIdForHistory(int id);

    void saveImage(MultipartFile file, String username);

    public void checkUserExperiencePoint(User user);

    public void checkUserLife(User user);

    List<User> getAllUsers(String user);

    void createChallengeEvent(ChallengeEventDto challengeEventDto, String username);

    List<ChallengeEventDto> getChallengeEventsByUser(User user);

    void performChallengeEvent(int theId, User loggedUser);

    void acceptChallengeEvent(int theId);

    void rejectChallengeEvent(int theId);

    void failChallengeEvent(int theId, User loggedUser);

    List<NotificationDto> getChallengeNotifications(User loggedUser);

    RealizationRecurringEvent getRealizationRecurringEventListByRealizationEventId(int eventId);
}
