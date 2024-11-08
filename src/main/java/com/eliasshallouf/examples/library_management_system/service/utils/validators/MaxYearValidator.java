package com.eliasshallouf.examples.library_management_system.service.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class MaxYearValidator implements ConstraintValidator<MaxYear, Integer> {
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        return year <= Year.now().getValue();
    }
}