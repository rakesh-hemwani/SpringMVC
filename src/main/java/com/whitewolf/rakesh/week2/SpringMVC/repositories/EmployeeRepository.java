package com.whitewolf.rakesh.week2.SpringMVC.repositories;

import com.whitewolf.rakesh.week2.SpringMVC.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
