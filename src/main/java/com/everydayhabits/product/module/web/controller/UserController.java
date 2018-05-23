package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.dto.ChallengeEventDto;
import com.everydayhabits.product.module.web.dto.NotificationDto;
import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.OneTimeEvent;
import com.everydayhabits.product.module.web.entity.RealizationRecurringEvent;
import com.everydayhabits.product.module.web.entity.RecurringEvent;
import com.everydayhabits.product.module.web.entity.User;
import com.everydayhabits.product.module.web.model.UploadForm;
import com.everydayhabits.product.module.web.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }



    @GetMapping("/")
    public String showDashboard(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<OneTimeEvent> oneTimeEventList = userService.getOneTimeEventsByUserId(loggedUser.getId());
        List<RecurringEvent> recurringEventList = userService.getRecurringEventsByUserId(loggedUser.getId());
        List<RealizationRecurringEvent> realizationRecurringEventList = userService.getRealizationRecurringEventList(recurringEventList);
        List<ChallengeEventDto> challengeEventDtos = userService.getChallengeEventsByUser(loggedUser);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        List<NotificationDto> notificationDtoList = userService.getChallengeNotifications(loggedUser);

        String encodedString = "";

        if (loggedUser.getImage() != null) {
            byte[] encoded = Base64.encodeBase64(loggedUser.getImage());
            encodedString = new String(encoded);
        }

        theModel.addAttribute("userImage", encodedString);
        theModel.addAttribute("fileBucket", new UploadForm());
        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("notificationDtoList", notificationDtoList);
        theModel.addAttribute("oneTimeEventList", oneTimeEventList);
        theModel.addAttribute("realizationRecurringEventList", realizationRecurringEventList);
        theModel.addAttribute("challengeEventList", challengeEventDtos);
        theModel.addAttribute("loggedUser", loggedUser);


        return "dashboard";
    }


    @GetMapping("/performOneTimeEvent")
    public String performOneTimeEvent(@RequestParam("eventId") int theId) {

        userService.performOneTimeEvent(theId);

        return "redirect:/";
    }

    @GetMapping("/acceptChallengeEvent")
    public String acceptChallengeEvent(@RequestParam("eventId") int theId) {

        userService.acceptChallengeEvent(theId);

        return "redirect:/";
    }

    @GetMapping("/rejectChallengeEvent")
    public String rejectChallengeEvent(@RequestParam("eventId") int theId) {

        userService.rejectChallengeEvent(theId);

        return "redirect:/";
    }

    @GetMapping("/performChallengeEvent")
    public String performChallengeEvent(@RequestParam("eventId") int theId) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        userService.performChallengeEvent(theId, loggedUser);

        return "redirect:/";
    }

    @GetMapping("/failChallengeEvent")
    public String failChallengeEvent(@RequestParam("eventId") int theId) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        userService.failChallengeEvent(theId, loggedUser);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdateOneTimeEvent")
    public String showFormForUpdateOneTimeEvent(@RequestParam("eventId") int eventId, Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);
        OneTimeEvent oneTimeEvent = userService.getOneTimeEventById(eventId);

        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("oneTimeEvent", oneTimeEvent);

        return "updateOneTimeEvent";
    }

    @PostMapping("/createOneTimeEvent")
    public String createOneTimeEvent(@ModelAttribute("challengeEvent") OneTimeEvent oneTimeEvent) {

        String username = getLoggedInUsername();

        System.out.println("Polskie znaki: " + oneTimeEvent.getDifficultyLevel());

        userService.createOneTimeEvent(oneTimeEvent, username);

        return "redirect:/";
    }

    @PostMapping("/createChallengeEvent")
    public String createChallengeEvent(@ModelAttribute("challenge") ChallengeEventDto challengeEventDto) {

        String username = getLoggedInUsername();

        userService.createChallengeEvent(challengeEventDto, username);

        return "redirect:/";
    }

    @PostMapping("/updateOneTimeEvent")
    public String updateOneTimeEvent(@ModelAttribute("oneTimeEvent") OneTimeEvent oneTimeEvent) {

        System.out.println("Polskie znaki: " + oneTimeEvent.getDifficultyLevel());

        userService.updateOneTimeEvent(oneTimeEvent);

        return "redirect:/";
    }

    @GetMapping("/deleteOneTimeEvent")
    public String deleteOneTimeEvent(@RequestParam("eventId") int eventId) {

        userService.deleteOneTimeEvent(eventId);

        return "redirect:/";
    }

    @GetMapping("/failOneTimeEvent")
    public String failOneTimeEvent(@RequestParam("eventId") int eventId) {

        String username = getLoggedInUsername();

        userService.failOneTimeEvent(eventId, username);

        return "redirect:/";
    }

    @PostMapping("/createRecurringEvent")
    public String createRecurringEvent(@ModelAttribute("recurringEvent") RecurringEvent recurringEvent) {

        String username = getLoggedInUsername();

        userService.createRecurringEvent(recurringEvent, username);

        return "redirect:/";
    }

    @GetMapping("/performRecurringEvent")
    public String performRecurringEvent(@RequestParam("eventId") int eventId) {

        userService.performRecurringEvent(eventId);

        return "redirect:/";
    }

    @PostMapping("/updateRecurringEvent")
    public String updateRecurringEvent(@ModelAttribute("recurringEvent") RecurringEvent recurringEvent) {

        userService.updateRecurringEvent(recurringEvent);

        return "redirect:/";
    }

    @GetMapping("/failRecurringEvent")
    public String failRecurringEvent(@RequestParam("eventId") int eventId) {

        String username = getLoggedInUsername();

        userService.failRecurringEvent(eventId, username);

        return "redirect:/";
    }

    @GetMapping("/skipRecurringEvent")
    public String skipRecurringEvent(@RequestParam("eventId") int eventId) {

        userService.skipRecurringEvent(eventId);

        return "redirect:/";
    }

    @GetMapping("/cancelOtherRecurringEvents")
    public String cancelOtherRecurringEvents(@RequestParam("eventId") int eventId) {

        userService.cancelOtherRecurringEvents(eventId);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdateRecurringEvent")
    public String showFormForUpdateRecurringEvent(@RequestParam("eventId") int eventId, Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);
        RecurringEvent recurringEvent = userService.getRecurringEventById(eventId);

        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("recurringEvent", recurringEvent);

        return "updateRecurringEvent";
    }


    @GetMapping("/showFormForUpdateUserPersonalData")
    public String showFormForUpdateUserPersonalData(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto>  notificationDtos = userService.getNotifications(loggedUser);

        UserDto userDto = userService.getUserDtoByUsername(username);

        theModel.addAttribute("userDto", userDto);
        theModel.addAttribute("notificationList", notificationDtos);

        return "updatePersonalData";
    }

    @PostMapping("/updatePersonalData")
    public String updatePersonalData(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult theBindingResult, Model theModel) throws Exception {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);


        List<NotificationDto>  notificationDtos = userService.getNotifications(loggedUser);
        theModel.addAttribute("notificationList", notificationDtos);

        if (theBindingResult.hasErrors()) {

            System.out.println(theBindingResult.getAllErrors());

            if (userDto.getFirstName().isEmpty()) {
                theBindingResult.rejectValue("firstName", "error.firstName");
            }

            if (userDto.getLastName().isEmpty()) {
                theBindingResult.rejectValue("lastName", "error.lastName");
            }
            if (!(userDto.getPassword().equals(userDto.getMatchingPassword()))) {
                theBindingResult.rejectValue("password", "error.password", "Passwords are different");
            }
            if (userDto.getCity().isEmpty()) {
                theBindingResult.rejectValue("city", "error.city");
            }

            return "updatePersonalData";
        } else {


            User registerUser = userService.validUserByUsername(userDto.getEmail());

            if (registerUser == null) {
                theBindingResult.rejectValue("email", "error.email", "Address email already exists");
            }

            userService.updateUserPersonalData(userDto);

            return "redirect:/";
        }
    }

    @GetMapping("/showFormForUpdateUserImage")
    public String showFormForUpdateUserImage(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        String encodedString = "";

        if (loggedUser.getImage() != null) {
            byte[] encoded = Base64.encodeBase64(loggedUser.getImage());
            encodedString = new String(encoded);
        }


        theModel.addAttribute("userImage", encodedString);
        theModel.addAttribute("notificationList", notificationDtos);

        return "updateUserImage";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/account")
    public String showMyAccount() {

        return "account";
    }

    @GetMapping("/rewards")
    public String showAwards(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        theModel.addAttribute("notificationList", notificationDtos);

        return "rewards";
    }


    @GetMapping("/ranking")
    public String showRanking(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        String encodedString = "";
        List<String> images = new ArrayList<>();

        List<User> userList = userService.getUsersByCriteria("allUsers", null);

        User currentUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        for (User user : userList) {
            if (user.getImage() != null) {
                byte[] encoded = Base64.encodeBase64(user.getImage());
                encodedString = new String(encoded);
                images.add(encodedString);
            } else {
                images.add("-");
            }

        }

        theModel.addAttribute("images", images);
        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("currentUser", currentUser);
        theModel.addAttribute("userList", userList);

        return "ranking";
    }

    @GetMapping("/getUsersByCriteria")
    public String getUsersByCriteria(@RequestParam("criteriaParam") String criteria, Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        String encodedString = "";
        List<String> images = new ArrayList<>();

        User currentUser = userService.getUserByUsername(username);

        List<User> userList = userService.getUsersByCriteria(criteria, username);
        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        for (User user : userList) {
            if (user.getImage() != null) {
                byte[] encoded = Base64.encodeBase64(user.getImage());
                encodedString = new String(encoded);
                images.add(encodedString);
                System.out.println();
            }
        }

        theModel.addAttribute("images", images);
        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("currentUser", currentUser);
        theModel.addAttribute("userList", userList);


        return "ranking";
    }



    @GetMapping("/oneTimeEvent")
    public String showOneTimeEvent(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("oneTimeEvent", new OneTimeEvent());

        return "oneTimeEvent";
    }

    @GetMapping("/challengeEvent")
    public String showFormForChallengeEvent(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);
        List<User> userList = userService.getAllUsers(username);


        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("challenge", new ChallengeEventDto());
//        theModel.addAttribute("user", new UserChallengeDto());
        theModel.addAttribute("userList", userList);

        return "challengeEvent";
    }

    @GetMapping("/reccuringEvent")
    public String showRecurringEvent(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<NotificationDto> notificationDtos = userService.getNotifications(loggedUser);

        theModel.addAttribute("notificationList", notificationDtos);
        theModel.addAttribute("recurringEvent", new RecurringEvent());

        return "recurringEvent";
    }

    @GetMapping("/history")
    public String showHistory(Model theModel) {

        String username = getLoggedInUsername();

        User currentUser = userService.getUserByUsername(username);

        List<OneTimeEvent> oneTimeEventList = userService.getOneTimeEventsByUserIdForHistory(currentUser.getId());


        List<RecurringEvent> recurringEventList = userService.getRecurringEventsByUserIdForHistory(currentUser.getId());

        List<RealizationRecurringEvent> realizationRecurringEventList = userService.getRealizationRecurringEventsByUserIdForHistory(recurringEventList);


        theModel.addAttribute("realizationRecurringEventList", realizationRecurringEventList);
        theModel.addAttribute("oneTimeEventList", oneTimeEventList);


        return "history";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

        String username = getLoggedInUsername();

        userService.saveImage(file, username);

        return "redirect:/";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    private String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


}
