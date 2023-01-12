package com.clairvoyant.employees.service;

import com.clairvoyant.employees.exception.EntityNotFoundException;
import com.clairvoyant.employees.exception.EntityValidationException;
import com.clairvoyant.employees.model.Employee;
import com.clairvoyant.employees.respository.EmployeeRepository;
import com.clairvoyant.employees.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        if (this.employeeRepository.findByEmail(employee.getEmail()) != null) {
            throw new EntityValidationException("Email already exists in database");
        }
        if(DateUtil.calculateAge(employee.getDateOfBirth(), LocalDate.now()) < 21 ||
                DateUtil.calculateAge(employee.getDateOfBirth(), LocalDate.now()) > 60) {
            throw  new EntityValidationException("Employee age should be between 21 and 60");
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = this.employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new EntityNotFoundException("Employee with email " + email + " not found");
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        Employee employee = this.getEmployeeByEmail(email);
        this.employeeRepository.deleteById(employee.getId());
    }
}
