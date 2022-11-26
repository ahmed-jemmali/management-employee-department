package com.springdemo.managementemployeedepartment.web;

import com.springdemo.managementemployeedepartment.entities.Employee;
import com.springdemo.managementemployeedepartment.models.EmployeeModel;
import com.springdemo.managementemployeedepartment.services.DepartmentService;
import com.springdemo.managementemployeedepartment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    private final DepartmentService departmentService;

    public EmployeeRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employees")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeModel employeeModel) {
        try {
            if (this.employeeService.getByEmployeeEmail(employeeModel.getEmployeeEmail()) != null)
                return ResponseEntity.status(400).body("Can not have multiple employee with the same email");
            Employee employee = new Employee();
            employee.setEmployeeFirstName(employeeModel.getEmployeeFirstName());
            employee.setEmployeeLastName(employeeModel.getEmployeeLastName());
            employee.setEmployeePhone(employeeModel.getEmployeePhoneNumber());
            employee.setEmployeeEmail(employeeModel.getEmployeeEmail());
            employee.setDepartment(this.departmentService.findOneDepartment(employeeModel.getDepartmentId()));
            this.employeeService.createEmployee(employee);
            return ResponseEntity.status(200).body("Employee added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = this.employeeService.getEmployees();
        return ResponseEntity.status(200).body(employees);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        Employee employee = this.employeeService.getEmployee(id);
        if (employee != null) return ResponseEntity.status(200).body(employee);
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeModel employeeModel, @PathVariable long id) {
        try {
            Employee employeeByEmail = this.employeeService.getByEmployeeEmail(employeeModel.getEmployeeEmail());
            if (employeeByEmail != null && employeeByEmail.getId() != id)
                return ResponseEntity.status(400).body("Can not have multiple employee with the same email");

            Employee employee = this.employeeService.getEmployee(id);
            if (employee != null) {
                employee.setEmployeeFirstName(employeeModel.getEmployeeFirstName());
                employee.setEmployeeLastName(employeeModel.getEmployeeLastName());
                employee.setEmployeePhone(employeeModel.getEmployeePhoneNumber());
                employee.setEmployeeEmail(employeeModel.getEmployeeEmail());
                employee.setDepartment(this.departmentService.findOneDepartment(employeeModel.getDepartmentId()));
                this.employeeService.updateEmployee(employee);
                return ResponseEntity.status(200).body("Employee updated successfully");
            } else
                return ResponseEntity.status(404).body("Employee does not exist");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        try {
            Employee employee = this.employeeService.getEmployee(id);
            if (employee != null) {
                this.employeeService.deleteEmployee(id);
                return ResponseEntity.ok().body("Employee deleted successfully");
            } else return ResponseEntity.status(404).body("Employee does not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }
}