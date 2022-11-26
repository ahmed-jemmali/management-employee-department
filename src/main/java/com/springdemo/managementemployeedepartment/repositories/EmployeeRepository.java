package com.springdemo.managementemployeedepartment.repositories;

import com.springdemo.managementemployeedepartment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getByEmployeeEmail(@Param("email") String email);
}