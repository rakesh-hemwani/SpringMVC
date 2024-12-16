package com.whitewolf.rakesh.week2.SpringMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PasswordValidation implements ConstraintValidator<PasswordValidator,String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{10,}$";

    @Override
    public boolean isValid(String inputPassword, ConstraintValidatorContext constraintValidatorContext){
        if(inputPassword == null) return false;
        return inputPassword.matches(PASSWORD_PATTERN);
    }

}
