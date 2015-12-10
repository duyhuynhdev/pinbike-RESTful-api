package me.pinbike.util.validator;

import me.pinbike.sharedjava.validation.annotation.IsExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hpduy17 on 12/3/15.
 */
public class IsExistValidator implements ConstraintValidator<IsExist, Long> {
    String message;

    public void initialize(IsExist constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    public boolean isValid(Long object, ConstraintValidatorContext constraintContext) {
        return true;
    }
}
