package com.whitewolf.rakesh.week2.SpringMVC.controllers;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping(path = "/home")
    public String getHomeMessage(){
        return "Welcome to Employee MVC world";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id, "Rakesh", "Rakes.Work", 27, LocalDate.of(2024,11,12),true);
    }

    @GetMapping(path = "/")
    public String getEmployeeDetails(@RequestParam(required = false) Integer age){
        if(age != null)
            return "Hi Employee Age is" + age;
        return "You have not passed the age";
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeePayload){
        employeePayload.setActive(true);
        employeePayload.setId(100L);
        return employeePayload;
    }

    @PutMapping String updateEmployeeID(){
        return "Update Employee";
    }
}
