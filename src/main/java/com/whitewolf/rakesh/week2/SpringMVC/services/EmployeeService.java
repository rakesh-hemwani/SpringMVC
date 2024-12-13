package com.whitewolf.rakesh.week2.SpringMVC.services;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import com.whitewolf.rakesh.week2.SpringMVC.entities.EmployeeEntity;
import com.whitewolf.rakesh.week2.SpringMVC.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeePayload){
        EmployeeEntity toSave = modelMapper.map(employeePayload, EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepository.save(toSave);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

}
