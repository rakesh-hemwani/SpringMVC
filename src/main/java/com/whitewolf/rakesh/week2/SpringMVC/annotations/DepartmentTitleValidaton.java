package com.whitewolf.rakesh.week2.SpringMVC.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {DepartmentTitleValidator.class})
public @interface DepartmentTitleValidaton {
    String message() default "Title of department can either be Manger developer or human resource";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
