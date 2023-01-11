package com.clairvoyant.employees.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;

    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name Cannot contain special characters")
    @NotNull(message = "First Name must not be null")
    @Size(min = 2, message = "First Name must have at least 2 characters")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name Cannot contain special characters")
    @NotNull(message = "Last Name must not be null")
    @Size(min = 2, message = "Last Name must have at least 2 characters")
    private String lastName;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    @NotNull(message = "Department Cannot be Null")
    private String department;

    private String designation;

}

