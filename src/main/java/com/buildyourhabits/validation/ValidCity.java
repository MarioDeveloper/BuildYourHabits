package com.buildyourhabits.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityValidator.class)
@Documented
public @interface ValidCity {
    String message() default "Pierwsz litera musi być duża";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
