package com.clairvoyant.employees.controllers;

import com.clairvoyant.employees.models.Employee;
import com.clairvoyant.employees.respository.EmployeeRepository;
import com.clairvoyant.employees.utils.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/employee")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employeeRequest) {
        if (this.employeeRepository.findByEmail(employeeRequest.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists in database", HttpStatus.BAD_REQUEST);
        }
        if(DateUtil.calculateAge(employeeRequest.getDateOfBirth(), LocalDate.now()) < 21 ||
                DateUtil.calculateAge(employeeRequest.getDateOfBirth(), LocalDate.now()) > 60) {
            return new ResponseEntity<>("Employee age should be between 21 and 60", HttpStatus.BAD_REQUEST);
        }
        this.employeeRepository.save(employeeRequest);
        return new ResponseEntity<>("Saved", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(this.employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Integer id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee savedEmployee = optionalEmployee.get();
            if (!savedEmployee.getEmail().equals(employee.getEmail())) {
                return new ResponseEntity<>("Email cannot be updated", HttpStatus.BAD_REQUEST);
            }
            if (!savedEmployee.getFirstName().equals(employee.getFirstName())) {
                return new ResponseEntity<>("First Name cannot be updated", HttpStatus.BAD_REQUEST);
            }
            if (!savedEmployee.getLastName().equals(employee.getLastName())) {
                return new ResponseEntity<>("Last Name cannot be updated", HttpStatus.BAD_REQUEST);
            }
            if (!savedEmployee.getDateOfBirth().equals(employee.getDateOfBirth())) {
                return new ResponseEntity<>("Date of Birth cannot be updated", HttpStatus.BAD_REQUEST);
            }
            savedEmployee.setDepartment(employee.getDepartment());
            savedEmployee.setDesignation(employee.getDesignation());
            this.employeeRepository.save(savedEmployee);
            return new ResponseEntity<>("Update employee with id " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if (employee.isPresent()) {
            this.employeeRepository.deleteById(id);
            return new ResponseEntity<>("Deleted employee with id " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
