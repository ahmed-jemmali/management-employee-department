package com.springdemo.managementemployeedepartment.services;

import com.springdemo.managementemployeedepartment.entities.Department;

import java.util.List;

public interface DepartmentService {

    void createDepartment(Department d);

    List<Department> findDepartments();

    Department findOneDepartment(long id);

    Department findByDepartmentName(String name);

    void updateDepartment(Department d);

    void deleteDepartment(long id);

}