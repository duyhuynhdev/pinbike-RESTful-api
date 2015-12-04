package me.pinbike.util.validator;

import me.pinbike.sharedjava.validation.annotation.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hpduy17 on 12/3/15.
 */
public class DoubleRangeValidator implements ConstraintValidator<Range, Double> {
    private long min;
    private long max;
    private String message;

    public void initialize(Range constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        message = constraintAnnotation.message();
    }

    public boolean isValid(Double object, ConstraintValidatorContext constraintContext) {
        if (object == null)
            return true;
        constraintContext.disableDefaultConstraintViolation();
        constraintContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return min <= object && object <= max;
    }

}
