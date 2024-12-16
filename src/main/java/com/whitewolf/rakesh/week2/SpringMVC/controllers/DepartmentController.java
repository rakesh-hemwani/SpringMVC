package com.whitewolf.rakesh.week2.SpringMVC.controllers;

import com.whitewolf.rakesh.week2.SpringMVC.dto.DepartmentDTO;
import com.whitewolf.rakesh.week2.SpringMVC.exceptions.ResourceNotFoundException;
import com.whitewolf.rakesh.week2.SpringMVC.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found : " + id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO departmentPayload){
        return new ResponseEntity<>(departmentService.createNewDepartment(departmentPayload), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentPaylaod, @PathVariable(name = "departmentId") Long id){
        return ResponseEntity.ok(departmentService.updateDepartmentByID(departmentPaylaod, id));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){
        boolean isDeleted = departmentService.deteleDepartmentByID(departmentId);
        if(isDeleted)
            return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}

