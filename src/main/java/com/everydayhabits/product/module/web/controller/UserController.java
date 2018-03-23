package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showMainPanel() {

        return "login";
    }


    @GetMapping("/dashboard")
    public String root() {
        return "dashboard";
    }


    @GetMapping("/picker")
    public String picker() {
        return "picker";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/account")
    public String showMyAccount() {

        return "account";
    }

    @GetMapping("/awards")
    public String showAwards() {

        return "user-rewards";
    }

    @GetMapping("/new")
    public String showNew() {

        return "new";
    }

    @GetMapping("/ranking")
    public String showRanking() {

        return "ranking";
    }

    @GetMapping("/oneTimeEvent")
    public String showOneTimeEvent() {

        return "oneTimeEvent";
    }

    @GetMapping("/reccuringEvent")
    public String showRecurringEvent() {

        return "recurring-event";
    }

    @GetMapping("/history")
    public String showHistory() {

        return "history";
    }


}
