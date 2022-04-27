package com.example.demo.validations.user;

import com.example.demo.validations.role.RoleIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = UserIdValidator.class)
public @interface ValidUserId {
    String message() default "Invalid user id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
