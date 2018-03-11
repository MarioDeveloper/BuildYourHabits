package com.everydayhabits.product.module.web.controller;

import com.everydayhabits.product.module.web.dto.UserDto;
import com.everydayhabits.product.module.web.entity.User;
import com.everydayhabits.product.module.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String processForm(@Valid @ModelAttribute("user") UserDto userDto, BindingResult theBindingResult) throws Exception {

        if (theBindingResult.hasErrors()) {

            if (userDto.getFirstName().isEmpty()) {
                theBindingResult.rejectValue("firstName", "error.firstName");
            }

            if (userDto.getLastName().isEmpty()) {
                theBindingResult.rejectValue("lastName", "error.lastName");
            }
            if (!(userDto.getPassword().equals(userDto.getMatchingPassword()))) {
                theBindingResult.rejectValue("password", "error.password", "Passwords are different");

            }

            return "registration";
        } else {
            System.out.println("first name: " + userDto.getFirstName());
            System.out.println("Email: " + userDto.getEmail());

            User registerUser = new User();

            registerUser = userService.registerNewUserAccount(userDto);

            if (registerUser == null) {
                theBindingResult.rejectValue("email", "error.email", "Address email already exists");
            }
            return "redirect:/registration?success";
        }
    }


}
