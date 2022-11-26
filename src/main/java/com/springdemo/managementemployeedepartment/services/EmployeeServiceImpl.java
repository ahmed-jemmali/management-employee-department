package com.springdemo.managementemployeedepartment.services;

import com.springdemo.managementemployeedepartment.entities.Employee;
import com.springdemo.managementemployeedepartment.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createEmployee(Employee e) {
        this.employeeRepository.save(e);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        if (this.employeeRepository.findById(id).isPresent())
            return this.employeeRepository.findById(id).get();
        else return null;
    }

    @Override
    public Employee getByEmployeeEmail(String email) {
        return this.employeeRepository.getByEmployeeEmail(email);
    }

    @Override
    public void updateEmployee(Employee e) {
        this.employeeRepository.save(e);
    }

    @Override
    public void deleteEmployee(long id) {
        this.employeeRepository.deleteById(id);
    }
}