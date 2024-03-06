package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.State;

public class StateValidation implements ConstraintValidator<State, String> {

    /*
    * @param value object to validate
    * @param context in which the constraint is evaluated
    *
    * @return
    *
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // validate value
        if (value == null) return false;
        return value.equals("published") || value.equals("draft");
    }

}
