package com.whitewolf.rakesh.week2.SpringMVC.controllers;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import com.whitewolf.rakesh.week2.SpringMVC.entities.EmployeeEntity;
import com.whitewolf.rakesh.week2.SpringMVC.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/home")
    public String getHomeMessage(){
        return "Welcome to Employee MVC world";
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //new EmployeeDTO(id, "Rakesh", "Rakes.Work", 27, LocalDate.of(2024,11,12),true);
        return employeeRepository.findById(id).orElse(null);
    }

//    @GetMapping(path = "/")
//    public String getEmployeeDetails(@RequestParam(required = false) Integer age){
//        if(age != null)
//            return "Hi Employee Age is" + age;
//        return "You have not passed the age";
//    }

    @GetMapping(path = "/all")
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeePayload){
//        employeePayload.setActive(true);
//        employeePayload.setId(100L);
        return employeeRepository.save(employeePayload);
    }

    @PutMapping String updateEmployeeID(){
        return "Update Employee";
    }
}
