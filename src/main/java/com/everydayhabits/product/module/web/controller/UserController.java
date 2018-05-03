package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.*;
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

        List<Notification> notificationList = userService.getNotifications();

        String encodedString = "";

        if (loggedUser.getImage() != null) {
            byte[] encoded = Base64.encodeBase64(loggedUser.getImage());
            encodedString = new String(encoded);
        }


        theModel.addAttribute("userImage", encodedString);
        theModel.addAttribute("fileBucket", new UploadForm());
        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("oneTimeEventList", oneTimeEventList);
        theModel.addAttribute("realizationRecurringEventList", realizationRecurringEventList);
        theModel.addAttribute("loggedUser", loggedUser);


        return "dashboard";
    }


    @GetMapping("/performOneTimeEvent")
    public String performOneTimeEvent(@RequestParam("eventId") int theId) {

        userService.performOneTimeEvent(theId);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdateOneTimeEvent")
    public String showFormForUpdateOneTimeEvent(@RequestParam("eventId") int eventId, Model theModel) {

        List<Notification> notificationList = userService.getNotifications();
        OneTimeEvent oneTimeEvent = userService.getOneTimeEventById(eventId);

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("oneTimeEvent", oneTimeEvent);

        return "updateOneTimeEvent";
    }

    @PostMapping("/createOneTimeEvent")
    public String saveOneTimeEvent(@ModelAttribute("oneTimeEvent") OneTimeEvent oneTimeEvent) {

        String username = getLoggedInUsername();

        System.out.println("Polskie znaki: " + oneTimeEvent.getDifficultyLevel());

        userService.createOneTimeEvent(oneTimeEvent, username);

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

        List<Notification> notificationList = userService.getNotifications();
        RecurringEvent recurringEvent = userService.getRecurringEventById(eventId);

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("recurringEvent", recurringEvent);

        return "updateRecurringEvent";
    }


    @GetMapping("/showFormForUpdateUserPersonalData")
    public String showFormForUpdateUserPersonalData(Model theModel) {

        String username = getLoggedInUsername();

        List<Notification> notificationList = userService.getNotifications();

        UserDto userDto = userService.getUserDtoByUsername(username);

        theModel.addAttribute("userDto", userDto);
        theModel.addAttribute("notificationList", notificationList);

        return "updatePersonalData";
    }

    @PostMapping("/updatePersonalData")
    public String updatePersonalData(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult theBindingResult, Model theModel) throws Exception {

        List<Notification> notificationList = userService.getNotifications();
        theModel.addAttribute("notificationList", notificationList);

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

        List<Notification> notificationList = userService.getNotifications();


        User loggedUser = userService.getUserByUsername(username);

        String encodedString = "";

        if (loggedUser.getImage() != null) {
            byte[] encoded = Base64.encodeBase64(loggedUser.getImage());
            encodedString = new String(encoded);
        }


        theModel.addAttribute("userImage", encodedString);
        theModel.addAttribute("notificationList", notificationList);

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

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);

        return "rewards";
    }


    @GetMapping("/ranking")
    public String showRanking(Model theModel) {

        String encodedString = "";
        List<String> images = new ArrayList<>();

        String username = getLoggedInUsername();

        List<User> userList = userService.getUsersByCriteria("allUsers", null);

        User currentUser = userService.getUserByUsername(username);

        List<Notification> notificationList = userService.getNotifications();

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
        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("currentUser", currentUser);
        theModel.addAttribute("userList", userList);

        return "ranking";
    }

    @GetMapping("/getUsersByCriteria")
    public String getUsersByCriteria(@RequestParam("criteriaParam") String criteria, Model theModel) {

        String encodedString = "";
        List<String> images = new ArrayList<>();

        String username = getLoggedInUsername();

        User currentUser = userService.getUserByUsername(username);

        List<User> userList = userService.getUsersByCriteria(criteria, username);
        List<Notification> notificationList = userService.getNotifications();

        for (User user : userList) {
            if (user.getImage() != null) {
                byte[] encoded = Base64.encodeBase64(user.getImage());
                encodedString = new String(encoded);
                images.add(encodedString);
                System.out.println();
            }
        }

        theModel.addAttribute("images", images);
        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("currentUser", currentUser);
        theModel.addAttribute("userList", userList);


        return "ranking";
    }



    @GetMapping("/oneTimeEvent")
    public String showOneTimeEvent(Model theModel) {

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("oneTimeEvent", new OneTimeEvent());

        return "oneTimeEvent";
    }

    @GetMapping("/reccuringEvent")
    public String showRecurringEvent(Model theModel) {

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);
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
