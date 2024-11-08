package com.eliasshallouf.examples.library_management_system.service.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxYearValidator.class)
public @interface MaxYear {
    String message() default "Year cannot be greater than the current year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}