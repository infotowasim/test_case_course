package org.happiestminds.springboottesting.service;

import org.happiestminds.springboottesting.model.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(long employeeId);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(long id);
}
