package com.whitewolf.rakesh.week2.SpringMVC.repositories;

import com.whitewolf.rakesh.week2.SpringMVC.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
