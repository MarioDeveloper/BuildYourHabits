package com.everydayhabits.product.module.web.validation;

import com.everydayhabits.product.module.web.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto user = (UserDto) obj;
        System.out.println(user.getPassword() + "   " + user.getMatchingPassword());
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
