package com.everydayhabits.product.module.web.service;

import com.everydayhabits.product.module.web.dto.ChallengeEventDto;
import com.everydayhabits.product.module.web.dto.NotificationDto;
import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.OneTimeEvent;
import com.everydayhabits.product.module.web.entity.RealizationRecurringEvent;
import com.everydayhabits.product.module.web.entity.RecurringEvent;
import com.everydayhabits.product.module.web.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends UserDetailsService {

    User registerNewUserAccount(UserDto registerUser);

    boolean emailExist(String email);

    User getUserByUsername(String username);

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

    User validUserByUsername(String email);

    void updateUserPersonalData(UserDto userDto);

    List<OneTimeEvent> getOneTimeEventsByUserIdForHistory(int id);

    List<RealizationRecurringEvent> getRealizationRecurringEventsByUserIdForHistory(List<RecurringEvent> recurringEventList);

    List<RecurringEvent> getRecurringEventsByUserIdForHistory(int id);

    void saveImage(MultipartFile file, String username);

    List<User> getAllUsers(String username);

    void createChallengeEvent(ChallengeEventDto challengeEventDto, String username);

    List<ChallengeEventDto> getChallengeEventsByUser(User user);

    void performChallengeEvent(int theId, User loggedUser);

    void acceptChallengeEvent(int theId);

    void rejectChallengeEvent(int theId);

    void failChallengeEvent(int theId, User loggedUser);

    List<NotificationDto> getChallengeNotifications(User loggedUser);

}
