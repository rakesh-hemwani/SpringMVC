package com.whitewolf.rakesh.week2.SpringMVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitewolf.rakesh.week2.SpringMVC.annotations.DepartmentTitleValidaton;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {

    private Long id;

    @DepartmentTitleValidaton
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 4, max = 16)
    private String title;

    @AssertTrue(message = "Departmet Should be Active")
    @JsonProperty("isActive")
    private Boolean isActive;

    @PastOrPresent(message = "Departmets can't be with future date creation")
    private LocalDate createdAt;

    @NotNull(message = "Salary Range can't be balnk")
    @Positive(message = "Salary Can't be negative")
    //@Digits(integer = 8, fraction = 2, message = "Invalid salary Digits")
    @Range(max = 1000000, min = 10000, message = "Salary Should be in range 10k to 10L only ")
    private Long salaryRange;
}
