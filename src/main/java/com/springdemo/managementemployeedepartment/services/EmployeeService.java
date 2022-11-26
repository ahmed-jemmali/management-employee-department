package com.springdemo.managementemployeedepartment.services;

import com.springdemo.managementemployeedepartment.entities.Employee;

import java.util.List;

public interface EmployeeService {

    void createEmployee(Employee e);

    List<Employee> getEmployees();

    Employee getEmployee(long id);

    Employee getByEmployeeEmail(String Email);

    void updateEmployee(Employee e);

    void deleteEmployee(long id);
}