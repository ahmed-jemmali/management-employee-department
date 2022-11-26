package com.springdemo.managementemployeedepartment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeEmail;
    @ManyToOne
    Department department;
}