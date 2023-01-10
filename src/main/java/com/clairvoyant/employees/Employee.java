package com.clairvoyant.employees;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;

    @NotNull
    @Size(min = 2, message = "First Name must have at least 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last Name must have at least 2 characters")
    private String lastName;

//    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    @NotNull(message = "Department Cannot be Null")
    private String department;

    private String designation;

}

