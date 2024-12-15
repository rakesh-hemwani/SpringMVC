package com.whitewolf.rakesh.week2.SpringMVC.controllers;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import com.whitewolf.rakesh.week2.SpringMVC.entities.EmployeeEntity;
import com.whitewolf.rakesh.week2.SpringMVC.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.
                map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(path = "/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeePayload){
        return new ResponseEntity<>(employeeService.createNewEmployee(employeePayload), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeByID(@RequestBody EmployeeDTO employeePayload, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeByID(employeePayload, employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deteleEmployeeByID(@PathVariable Long employeeId){
        boolean isDeleted = employeeService.deteleEmployeeByID(employeeId);
        if(isDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeByID(@RequestBody Map<String, Object> updateFields, @PathVariable Long employeeId){
        Optional<EmployeeDTO> updatedEmployee = employeeService.updatePartialEmployeeByID(updateFields, employeeId);
        return updatedEmployee
                .map(employeeDTO -> ResponseEntity.ok(employeeDTO)).
                orElse(ResponseEntity.notFound().build());
    }
}
