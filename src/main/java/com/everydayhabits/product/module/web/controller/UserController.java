package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.entity.*;
import com.everydayhabits.product.module.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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


    @GetMapping("/dashboard")
    public String showDashboard(Model theModel) {

        String username = getLoggedInUsername();
        User loggedUser = userService.getUserByUsername(username);

        List<OneTimeEvent> oneTimeEventList = userService.getOneTimeEventsByUserId(loggedUser.getId());
        List<RecurringEvent> recurringEventList = userService.getRecurringEventsByUserId(loggedUser.getId());
        List<RealizationRecurringEvent> realizationRecurringEventList = userService.getRealizationRecurringEventList(recurringEventList);

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("oneTimeEventList", oneTimeEventList);
        theModel.addAttribute("realizationRecurringEventList", realizationRecurringEventList);
        theModel.addAttribute("loggedUser", loggedUser);


        return "dashboard";
    }


    @GetMapping("/performOneTimeEvent")
    public String performOneTimeEvent(@RequestParam("eventId") int theId) {

        userService.performOneTimeEvent(theId);

        return "redirect:/dashboard";
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

        return "redirect:/dashboard";
    }

    @PostMapping("/updateOneTimeEvent")
    public String updateOneTimeEvent(@ModelAttribute("oneTimeEvent") OneTimeEvent oneTimeEvent) {

        System.out.println("Polskie znaki: " + oneTimeEvent.getDifficultyLevel());

        userService.updateOneTimeEvent(oneTimeEvent);

        return "redirect:/dashboard";
    }

    @GetMapping("/deleteOneTimeEvent")
    public String deleteOneTimeEvent(@RequestParam("eventId") int eventId) {

        userService.deleteOneTimeEvent(eventId);

        return "redirect:/dashboard";
    }

    @GetMapping("/failOneTimeEvent")
    public String failOneTimeEvent(@RequestParam("eventId") int eventId) {

        String username = getLoggedInUsername();

        userService.failOneTimeEvent(eventId, username);

        return "redirect:/dashboard";
    }

    @PostMapping("/createRecurringEvent")
    public String createRecurringEvent(@ModelAttribute("recurringEvent") RecurringEvent recurringEvent) {

        String username = getLoggedInUsername();

        userService.createRecurringEvent(recurringEvent, username);

        return "redirect:/dashboard";
    }

    @GetMapping("/performRecurringEvent")
    public String performRecurringEvent(@RequestParam("eventId") int eventId) {

        userService.performRecurringEvent(eventId);

        return "redirect:/dashboard";
    }

    @PostMapping("/updateRecurringEvent")
    public String updateRecurringEvent(@ModelAttribute("recurringEvent") RecurringEvent recurringEvent) {

        userService.updateRecurringEvent(recurringEvent);

        return "redirect:/dashboard";
    }

    @GetMapping("/failRecurringEvent")
    public String failRecurringEvent(@RequestParam("eventId") int eventId) {

        String username = getLoggedInUsername();

        userService.failRecurringEvent(eventId, username);

        return "redirect:/dashboard";
    }

    @GetMapping("/skipRecurringEvent")
    public String skipRecurringEvent(@RequestParam("eventId") int eventId) {

        userService.skipRecurringEvent(eventId);

        return "redirect:/dashboard";
    }

    @GetMapping("/cancelOtherRecurringEvents")
    public String cancelOtherRecurringEvents(@RequestParam("eventId") int eventId) {

        userService.cancelOtherRecurringEvents(eventId);

        return "redirect:/dashboard";
    }

    @GetMapping("/showFormForUpdateRecurringEvent")
    public String showFormForUpdateRecurringEvent(@RequestParam("eventId") int eventId, Model theModel) {

        List<Notification> notificationList = userService.getNotifications();
        RecurringEvent recurringEvent = userService.getRecurringEventById(eventId);

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("recurringEvent", recurringEvent);

        return "updateRecurringEvent";
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

        String username = getLoggedInUsername();

        List<User> userList = userService.getUsersByCriteria("allUsers", null);

        User currentUser = userService.getUserByUsername(username);

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);
        theModel.addAttribute("currentUser", currentUser);
        theModel.addAttribute("userList", userList);

        return "ranking";
    }

    @GetMapping("/getUsersByCriteria")
    public String getUsersByCriteria(@RequestParam("criteriaParam") String criteria, Model theModel) {

        String username = getLoggedInUsername();

        User currentUser = userService.getUserByUsername(username);

        List<User> userList = userService.getUsersByCriteria(criteria, username);

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

        List<Notification> notificationList = userService.getNotifications();

        theModel.addAttribute("notificationList", notificationList);

        return "history";
    }

    private String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


}
