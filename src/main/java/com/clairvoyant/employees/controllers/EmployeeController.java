package com.clairvoyant.employees.controllers;

import com.clairvoyant.employees.dto.EmployeeDto;
import com.clairvoyant.employees.dto.EmployeeUpdateDto;
import com.clairvoyant.employees.model.Employee;
import com.clairvoyant.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/employee")
@Validated
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        logger.info("Received creation request for employee addition");
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        this.employeeService.addEmployee(employee);
        logger.info("Employee with email {} created successfully", employeeDto.getEmail());
        return new ResponseEntity<>("Saved Employee", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        logger.info("Received request for getting all employees");
        List<Employee> employees = this.employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtoList = employees
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeDto.class))
                .toList();
        logger.info("Sending employee list of size {}", employeeDtoList.size());
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        logger.info("Received request for getting employee with email {}", email);
        Employee employee = this.employeeService.getEmployeeByEmail(email);
        EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);
        logger.info("Sending employee details with email {}", email);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PutMapping(path = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto,
                                                 @PathVariable String email) {
        logger.info("Received request for updating employee with email {}", email);
        Employee employee = this.employeeService.getEmployeeByEmail(email);
        this.modelMapper.map(employeeUpdateDto, employee);
        this.employeeService.updateEmployee(employee);
        logger.info("Updated employee with email {}", email);
        return new ResponseEntity<>("Updated Employee with email " + email, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{email}")
    public ResponseEntity<String> deleteEmployeeByEmail(@PathVariable String email) {
        logger.info("Received request for deleting employee with email {}", email);
        this.employeeService.deleteEmployeeByEmail(email);
        logger.info("Deleted employee with email {}", email);
        return new ResponseEntity<>("Deleted employee with email " + email, HttpStatus.NOT_FOUND);
    }
}
