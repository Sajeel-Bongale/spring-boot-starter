package com.clairvoyant.employees.controllers;

import com.clairvoyant.employees.dto.EmployeeDto;
import com.clairvoyant.employees.dto.EmployeeUpdateDto;
import com.clairvoyant.employees.model.Employee;
import com.clairvoyant.employees.respository.EmployeeRepository;
import com.clairvoyant.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/employee")
@Validated
public class EmployeeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        this.employeeService.addEmployee(employee);
        return new ResponseEntity<>("Saved Employee", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtoList = employees
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = this.employeeService.getEmployeeByEmail(email);
        EmployeeDto employeeDto = this.modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PutMapping(path = "/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto,
                                                 @PathVariable String email) {
        Employee employee = this.employeeService.getEmployeeByEmail(email);
        this.modelMapper.map(employeeUpdateDto, employee);
        this.employeeService.updateEmployee(employee);
        return new ResponseEntity<>("Updated Employee with email " + email, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{email}")
    public ResponseEntity<String> deleteEmployeeByEmail(@PathVariable String email) {
        this.employeeService.deleteEmployeeByEmail(email);
        return new ResponseEntity<>("Deleted employee with email " + email, HttpStatus.NOT_FOUND);
    }
}
