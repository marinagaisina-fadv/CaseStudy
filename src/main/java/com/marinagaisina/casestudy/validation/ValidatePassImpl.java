package com.marinagaisina.casestudy.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePassImpl implements ConstraintValidator<ValidatePass, String> {
    public static final Logger LOG = LoggerFactory.getLogger(ValidatePassImpl.class);

    @Override
    public void initialize(ValidatePass constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // a custom validation should validate 1 and only 1 thing.
        // since a check for not null or not empty already exists
        // we want this function to return true in that case which will
        // prevent the error message for this validation from displaying
        // when the incoming value is null or empty.
        // this is a good design pattern and should be implemented as the first
        // thing in all custom validations.
        if (value == null) return true;
        if ((value.length() < 7) || (value.length() > 100)) {
            return true;
        }
        return passwordValidates(value);
    }

    private boolean passwordValidates(String pass) {
        int count = 0;

        if( pass.matches(".*\\d.*") )
            count ++;
        if( pass.matches(".*[a-z].*") )
            count ++;
        if( pass.matches(".*[A-Z].*") )
            count ++;
        if( pass.matches(".*[*.!@#$%^&(){}\\[\\]:;\"'<>,?/~`_+\\-=|].*"))
        count ++;

        return count==4;
    }
}
