package org.happiestminds.springboottesting.service.impl;

import org.happiestminds.springboottesting.exception.ResourceNotFoundException;
import org.happiestminds.springboottesting.model.Employee;
import org.happiestminds.springboottesting.repository.EmployeeRepository;
import org.happiestminds.springboottesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {



    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(employee.getEmail());

        if (employeeOptional.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:"+employee.getEmail());
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
    public Optional<Employee> getEmployeeById(long employeeId){
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        return employeeOptional;
    }


    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }


    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
