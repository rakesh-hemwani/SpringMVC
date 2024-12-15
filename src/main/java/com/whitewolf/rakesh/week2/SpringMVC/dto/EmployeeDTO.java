package com.whitewolf.rakesh.week2.SpringMVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitewolf.rakesh.week2.SpringMVC.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name of employee cannot be blank")
    @Size(min = 3, max = 10, message = "Length of Name is invalid")
    private String name;

    @Email(message = "Email is not Valid")
    private String email;

    @NotNull(message = "Age can't be null")
    @Max(value = 80, message = "Employee can't be above 80")
    @Min(value = 18, message = "Employee Can't be below 18")
    private Integer age;

    @PastOrPresent(message = "Date of joining can't be in future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee Should be Active")
    @JsonProperty("isActive")
    private Boolean isActive;

    @NotNull(message = "Salary Can't be null")
    @Positive(message = "Salary can't be negative or Zero")
    @Digits(integer = 6, fraction = 2, message = "The salary can't be more than xxxxxx.yy format ")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @NotBlank(message = "Role can't be blank")
    //@Pattern(regexp = "^(ADMIN||USER)$")
    @EmployeeRoleValidation
    private String role;

}
