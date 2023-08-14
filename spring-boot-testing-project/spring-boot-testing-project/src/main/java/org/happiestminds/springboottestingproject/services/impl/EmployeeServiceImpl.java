package org.happiestminds.springboottestingproject.services.impl;

import org.happiestminds.springboottestingproject.entities.Employee;
import org.happiestminds.springboottestingproject.exception.ResourceNotFoundException;
import org.happiestminds.springboottestingproject.repositories.EmployeeRepository;
import org.happiestminds.springboottestingproject.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee saveEmployee(Employee employee){

        // if employee already exist with given email then it should be throws exception, it should be unique record.
          // finding employee object by using findByEmail()method from database
        Optional<Employee> existEmployeeObject = employeeRepository.findByEmail(employee.getEmail());
        if (existEmployeeObject.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }

        // save employee object into database
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
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee;
    }



    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());

        return updatedEmployee;
    }



//    @Override
//    public Employee updateEmployee(Employee updatedEmployee) {
//        Employee saveEmployee = employeeRepository.save(updatedEmployee);
//        return saveEmployee;
//    }


    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

}
