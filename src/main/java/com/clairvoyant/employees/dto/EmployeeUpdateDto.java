package com.clairvoyant.employees.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdateDto {
    @NotNull(message = "Department Cannot be Null")
    private String department;

    private String designation;
}
