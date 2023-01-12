package com.clairvoyant.employees.respository;


import com.clairvoyant.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Employee findByEmail(String email);

/*
    @Query("select a from Article a where a.creationDateTime <= :creationDateTime")
    List<Employee> findAllWithCreationDateTimeBefore(
            @Param("creationDateTime") Date creationDateTime);
*/
}
