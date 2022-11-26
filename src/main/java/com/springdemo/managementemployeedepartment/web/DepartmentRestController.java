package com.springdemo.managementemployeedepartment.web;

import com.springdemo.managementemployeedepartment.entities.Department;
import com.springdemo.managementemployeedepartment.models.DepartmentModel;
import com.springdemo.managementemployeedepartment.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentModel model) {
        try {
            if (this.departmentService.findByDepartmentName(model.getDepartmentName()) != null)
                return ResponseEntity.status(400).body("Can not have multiple department with the same name");
            Department department = new Department();
            department.setDepartmentName(model.getDepartmentName());
            this.departmentService.createDepartment(department);
            return ResponseEntity.ok("Department added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = this.departmentService.findDepartments();
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getOneDepartment(@PathVariable long id) {
        Department department = this.departmentService.findOneDepartment(id);
        if (department != null) return ResponseEntity.status(HttpStatus.OK).body(department);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> updateDepartment(@RequestBody DepartmentModel model, @PathVariable long id) {
        try {
            Department department = this.departmentService.findOneDepartment(id);
            if (department != null) {
                if (Objects.equals(department.getDepartmentName(), model.getDepartmentName()))
                    return ResponseEntity.status(400).body("Can not have multiple department with the same name");
                department.setDepartmentName(model.getDepartmentName());
                this.departmentService.updateDepartment(department);
                return ResponseEntity.ok().body("Department updated successfully");
            } else
                return ResponseEntity.status(404).body("Department does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable long id) {
        try {
            if (this.departmentService.findOneDepartment(id) != null) {
                this.departmentService.deleteDepartment(id);
                return ResponseEntity.status(200).body("Department deleted successfully");
            } else return ResponseEntity.status(404).body("Department does not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }
}