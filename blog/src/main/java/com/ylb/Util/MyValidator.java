package com.ylb.Util;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class MyValidator {
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true)
            .buildValidatorFactory().getValidator();

    public static <T> String validate(T domain) {
        Set<ConstraintViolation<T>> validate = validator.validate(domain);
        if (validate.size() > 0) {
         return validate.iterator().next().getMessage();
        }
        return null;
    }
}
