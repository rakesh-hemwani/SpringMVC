package com.whitewolf.rakesh.week2.SpringMVC.services;

import com.whitewolf.rakesh.week2.SpringMVC.dto.DepartmentDTO;
import com.whitewolf.rakesh.week2.SpringMVC.entities.DepartmentEntity;
import com.whitewolf.rakesh.week2.SpringMVC.exceptions.ResourceNotFoundException;
import com.whitewolf.rakesh.week2.SpringMVC.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public void isDepartmentExist(Long id){
        boolean exists = departmentRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Department with Id does not exist");
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments(){
        return departmentRepository.findAll()
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO departmentPayload){
        DepartmentEntity toSave = modelMapper.map(departmentPayload, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(toSave);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentByID(DepartmentDTO departmentPayload, Long departmentId) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentPayload, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public Boolean deteleDepartmentByID(Long departmentId) {
        isDepartmentExist(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }
}
