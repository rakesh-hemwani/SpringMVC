package com.whitewolf.rakesh.week2.SpringMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class DepartmentTitleValidator implements ConstraintValidator<DepartmentTitleValidaton,String> {
    @Override
    public boolean isValid(String inputTitle, ConstraintValidatorContext constraintValidatorContext) {

        if(inputTitle == null) return false;
        List<String> titles = List.of("Human Resources", "Development", "Management");
        return titles.contains(inputTitle);
    }
}
