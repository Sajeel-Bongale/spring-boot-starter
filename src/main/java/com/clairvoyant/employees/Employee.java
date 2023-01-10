package com.clairvoyant.employees;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;

    @NotBlank(message = "First Name Cannot be Blank")
    @Size(min = 2, message = "First Name must have at least 2 characters")
    private String firstName;

    @NotEmpty(message = "Last Name Cannot be Empty")
    private String lastName;

//    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    @NotNull(message = "First Name Cannot be Null")
    private String department;

    private String designation;

}

