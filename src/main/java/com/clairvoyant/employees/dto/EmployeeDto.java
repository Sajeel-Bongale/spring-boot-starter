package com.clairvoyant.employees.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name Cannot contain special characters")
    @NotNull(message = "First Name must not be null")
    @Size(min = 2, message = "First Name must have at least 2 characters")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name Cannot contain special characters")
    @NotNull(message = "Last Name must not be null")
    @Size(min = 2, message = "Last Name must have at least 2 characters")
    private String lastName;

    private LocalDate dateOfBirth;

    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Department Cannot be Null")
    private String department;

    private String designation;
}
