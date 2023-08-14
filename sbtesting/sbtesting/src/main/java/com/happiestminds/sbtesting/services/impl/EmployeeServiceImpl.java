package com.happiestminds.sbtesting.services.impl;

import com.happiestminds.sbtesting.entities.Employee;
import com.happiestminds.sbtesting.exception.ResourseNotFoundException;
import com.happiestminds.sbtesting.repositories.EmployeeRepository;
import com.happiestminds.sbtesting.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> existsEmployeeObject = employeeRepository.findByEmail(employee.getEmail());

        if (existsEmployeeObject.isPresent()){
            throw new ResourseNotFoundException("Employee already exist with given email"+ employee.getEmail());
        }

        Employee saveEmployee = employeeRepository.save(employee);
        return saveEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Optional<Employee> getEmployeeById(long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        return employeeOptional;
    }

    @Override
    public Employee updateEmployee(Employee savedEmployee) {
        Employee UpdatedEmployee = employeeRepository.save(savedEmployee);
        return UpdatedEmployee;
    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);

    }
}
