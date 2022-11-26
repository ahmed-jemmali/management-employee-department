package com.springdemo.managementemployeedepartment.services;

import com.springdemo.managementemployeedepartment.entities.Department;
import com.springdemo.managementemployeedepartment.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createDepartment(Department d) {
        this.departmentRepository.save(d);
    }

    @Override
    public List<Department> findDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department findOneDepartment(long id) {
        if (this.departmentRepository.findById(id).isPresent())
            return this.departmentRepository.findById(id).get();
        else return null;
    }

    @Override
    public Department findByDepartmentName(String name) {
        return this.departmentRepository.findByDepartmentName(name);
    }

    @Override
    public void updateDepartment(Department d) {
        this.departmentRepository.save(d);
    }

    @Override
    public void deleteDepartment(long id) {
        this.departmentRepository.deleteById(id);
    }
}