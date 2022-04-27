package com.example.demo.validations.email;

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
@Target({FIELD, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
