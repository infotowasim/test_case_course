package com.happiestminds.sbtesting.repositories;


import com.happiestminds.sbtesting.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    // Custom Query for findByEmail
    Optional<Employee> findByEmail(String email);


    // Define custom query using JPQL with index parameter
    @Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
    Employee findByJPQL(String firstName, String lastName);


    // custom query using JPQL named parameters
    @Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
    Employee findByJPQLNamedParam(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName);


    // custom query using native SQL with index parameters
    @Query(value = "select * from employees e where e.first_name=?1 and e.last_name=?2", nativeQuery = true)
    Employee findByNativeSQLIndexParam(String firstName, String lastName);


    // custom query using native SQL with named parameters
    @Query(value = "select * from employees e where e.first_name=:firstName and e.last_name=:lastName", nativeQuery = true)
    Employee findByNativeSQLNamedParam(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName);
}
