package com.clairvoyant.employees;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByEmail(String email);
}
