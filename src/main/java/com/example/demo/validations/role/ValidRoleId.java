package com.example.demo.validations.role;

import com.example.demo.validations.username.UsernameValidator;

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
@Constraint(validatedBy = RoleIdValidator.class)
public @interface ValidRoleId {
    String message() default "Invalid role id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
