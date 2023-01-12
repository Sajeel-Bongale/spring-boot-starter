package com.clairvoyant.employees.service;

import com.clairvoyant.employees.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeByEmail(String email);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeByEmail(String email);
}
