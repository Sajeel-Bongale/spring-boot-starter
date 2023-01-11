package com.clairvoyant.employees.respository;


import com.clairvoyant.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByEmail(String email);

/*
    @Query("select a from Article a where a.creationDateTime <= :creationDateTime")
    List<Employee> findAllWithCreationDateTimeBefore(
            @Param("creationDateTime") Date creationDateTime);
*/
}
