package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.DTO.UserDto;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showMainPanel() {

        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/processRegistrationForm")
    public String processForm(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {

            theBindingResult.rejectValue("firstName", "error.firstName", "Field is required");
            theBindingResult.rejectValue("lastName", "error.lastName", "Field is required");
//            theBindingResult.rejectValue("email", "error.email", "Wrong email");
            theBindingResult.rejectValue("password", "error.password", "Passwords are different");

            return "registration";
        }
        else {
            return "login";
        }
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
