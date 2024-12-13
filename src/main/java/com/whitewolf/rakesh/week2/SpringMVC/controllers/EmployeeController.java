package com.whitewolf.rakesh.week2.SpringMVC.controllers;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import com.whitewolf.rakesh.week2.SpringMVC.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/home")
    public String getHomeMessage(){
        return "Welcome to Employee MVC world";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }


    @GetMapping(path = "/all")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeePayload){
        return employeeService.createNewEmployee(employeePayload);
    }

    @PutMapping String updateEmployeeID(){
        return "Update Employee";
    }
}
