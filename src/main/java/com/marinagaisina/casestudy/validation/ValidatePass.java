package com.marinagaisina.casestudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatePassImpl.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePass {

        String message() default "{ValidatePass}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
