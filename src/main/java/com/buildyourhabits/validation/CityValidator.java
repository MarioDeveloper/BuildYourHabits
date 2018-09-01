package com.buildyourhabits.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CityValidator implements ConstraintValidator<ValidCity, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String CITY_PATTERN = "[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+";

    @Override
    public void initialize(ValidCity constraintAnnotation) {
    }

    @Override
    public boolean isValid(String city, ConstraintValidatorContext context) {
        return (validateCity(city));
    }

    private boolean validateCity(String city) {
        pattern = Pattern.compile(CITY_PATTERN);
        matcher = pattern.matcher(city);
        return matcher.matches();
    }
}
