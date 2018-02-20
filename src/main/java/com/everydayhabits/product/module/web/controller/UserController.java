package com.everydayhabits.product.module.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String showMainPanel() {

        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationPge() {

        return "registration";
    }

    @GetMapping("/account")
    public String showMyAccount() {

        return "account";
    }

    @GetMapping("/awards")
    public String showAwards() {

        return "awards";
    }

    @GetMapping("/ranking")
    public String showRanking() {

        return "ranking";
    }

    @GetMapping("/oneTimeEvent")
    public String showOneTimeEvent() {

        return "one-time-event";
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
