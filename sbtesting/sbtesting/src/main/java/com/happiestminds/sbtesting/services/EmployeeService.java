package com.happiestminds.sbtesting.services;

import com.happiestminds.sbtesting.entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(long employeeId);

    Employee updateEmployee(Employee savedEmployee);

    void deleteEmployee(long employeeId);
}
