package com.whitewolf.rakesh.week2.SpringMVC.services;

import com.whitewolf.rakesh.week2.SpringMVC.dto.EmployeeDTO;
import com.whitewolf.rakesh.week2.SpringMVC.entities.EmployeeEntity;
import com.whitewolf.rakesh.week2.SpringMVC.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public boolean isEmployeeExist(Long id){
        return employeeRepository.existsById(id);
    }

    public Optional<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeRepository.findById(id)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeByID(EmployeeDTO employeePayload, Long employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeePayload, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public Boolean deteleEmployeeByID(Long employeeId) {
        if(!isEmployeeExist(employeeId))
            return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public Optional<EmployeeDTO> updatePartialEmployeeByID(Map<String, Object> updateFields, Long employeeId) {
        return employeeRepository.findById(employeeId).map(employeeEntity -> {
                updateFields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findRequiredField(EmployeeEntity.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,employeeEntity,value);
                });
                return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
        });
    }
}
