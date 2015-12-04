package me.pinbike.util.validator;

import me.pinbike.sharedjava.validation.annotation.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hpduy17 on 12/3/15.
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
    private String message;

    public void initialize(NotEmpty constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null)
            return true;
        constraintContext.disableDefaultConstraintViolation();
        constraintContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return !object.isEmpty();
    }

}
