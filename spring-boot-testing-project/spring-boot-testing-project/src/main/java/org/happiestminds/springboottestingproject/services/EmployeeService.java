package org.happiestminds.springboottestingproject.services;

import org.happiestminds.springboottestingproject.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

   //Save employee object
    Employee saveEmployee(Employee employee);

    //Get all employee object
    List<Employee> getAllEmployee();

    //Get employee by id
    Optional<Employee> getEmployeeById(long employeeId);

    //Update employee
    Employee updateEmployee(Employee updatedEmployee);

    //Delete employee
    void deleteEmployee(long id);
}
