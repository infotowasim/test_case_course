package org.happiestminds.springboottesting.controller;

import lombok.extern.slf4j.Slf4j;
import org.happiestminds.springboottesting.model.Employee;
import org.happiestminds.springboottesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee){
        log.info("employee: {}", employee);
        Employee saveEmployee = employeeService.saveEmployee(employee);
        return saveEmployee;
    }


    @GetMapping
    public List<Employee> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return employeeList;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
        return employeeById.map(ResponseEntity::ok)
                .orElseGet( ()-> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){


        return employeeService.getEmployeeById(employeeId)
                .map(savedEmployee -> {
                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setLastName(employee.getLastName());
                    savedEmployee.setEmail(employee.getEmail());

                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
                    return new ResponseEntity(updatedEmployee,HttpStatus.OK);
                })
                .orElseGet(()-> ResponseEntity.notFound().build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee Deleted Successfully",HttpStatus.OK);
    }

}
