package org.happiestminds.springboottestingproject.controller;

import org.happiestminds.springboottestingproject.entities.Employee;
import org.happiestminds.springboottestingproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
////
////    @Autowired
////    private EmployeeService employeeService;
////
////    // Create Employee
////    @PostMapping
////    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
////        Employee saveEmployee = employeeService.saveEmployee(employee);
////        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
////    }
////
////    // Get All Employee
////    @GetMapping
////    @ResponseStatus(HttpStatus.OK)
////    public List<Employee> getAllEmployee(){
////        List<Employee> employeeList = employeeService.getAllEmployee();
////        return employeeList;
////    }
////
//////    @GetMapping("/{id}")
//////    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
//////        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
//////        return employeeById.map(ResponseEntity::ok).orElseGet( ()-> ResponseEntity.notFound().build());
//////    }
////
////    // Get Employee By Id
////    @GetMapping("/{id}")
////    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
////        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
////        return employeeById.map(ResponseEntity::ok).orElseGet( ()-> ResponseEntity.notFound().build());
////    }
////
//////    // Update Employee
//////    @PutMapping("/{id}")
//////    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId) {
//////        return employeeService.getEmployeeById(employeeId)
//////                .map(savedEmployee -> {
//////                    savedEmployee.setFirstName(employee.getFirstName());
//////                    savedEmployee.setLastName(employee.getLastName());
//////                    savedEmployee.setEmail(employee.getEmail());
//////
//////                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
//////                    return ResponseEntity.ok(updatedEmployee);
//////                })
//////                .orElseGet(() -> ResponseEntity.notFound().build());
//////    }
////
//////    // Update Employee
//////    @PutMapping("/{id}")
//////    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId){
//////        return employeeService.getEmployeeById(employeeId)
//////                .map(savedEmployee -> {
//////                    savedEmployee.setFirstName(employee.getFirstName());
//////                    savedEmployee.setLastName(employee.getLastName());
//////                    savedEmployee.setEmail(employee.getEmail());
//////
//////                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
//////                    return ResponseEntity.ok(updatedEmployee);
//////
//////                })
//////                .orElseGet( ()-> ResponseEntity.notFound().build());
//////
//////    }
////
////
////    // Update Employee
////    @PutMapping("/{id}")
////    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){
////
////        return employeeService.getEmployeeById(employeeId)
////                .map( savedEmployee -> {
////                    savedEmployee.setFirstName(employee.getFirstName());
////                    savedEmployee.setLastName(employee.getLastName());
////                    savedEmployee.setEmail(employee.getEmail());
////
////                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
////                    return ResponseEntity.ok(updatedEmployee);
////
////
////                }).orElseGet( ()-> ResponseEntity.notFound().build());
////    }
////
////
////    // delete employee
////    @DeleteMapping("/{id}")
////    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
////        employeeService.deleteEmployee(employeeId);
////        return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
////    }
//
//
//
//
//
//
//
//
//
//
//
//    @Autowired
//    private EmployeeService employeeService;
//
//
//    // Create Employee
//    @PostMapping
//    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
//        Employee savedEmployee = employeeService.saveEmployee(employee);
//        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
//    }
//
//    // Get All Employee
//    @GetMapping
//    public ResponseEntity<List<Employee>> getAllEmployee(){
//        List<Employee> employeeList = employeeService.getAllEmployee();
//        return ResponseEntity.ok(employeeList);
//    }
//
//    // Get Employee By Id
//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
//        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
//        return employeeById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Update Employee
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId){
//        return employeeService.getEmployeeById(employeeId)
//                .map( savedEmployee ->{
//                    savedEmployee.setFirstName(employee.getFirstName());
//                    savedEmployee.setLastName(employee.getLastName());
//                    savedEmployee.setEmail(employee.getEmail());
//
//                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
//                    return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
//                })
//                .orElseGet( ()-> ResponseEntity.notFound().build());
//    }
//
//    //delete employee
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
//        employeeService.deleteEmployee(employeeId);
//        return ResponseEntity.ok("Employee deleted successfully");
//    }



    @Autowired
    private EmployeeService employeeService;


    // create employee
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    // get all employee
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeList);
    }


    // get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
        return employeeById.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }


    // update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") long employeeId){
        Optional<Employee> employeeById = employeeService.getEmployeeById(employeeId);
        return employeeById.map( (savedEmployee)->{
            savedEmployee.setFirstName(employee.getFirstName());
            savedEmployee.setLastName(employee.getLastName());
            savedEmployee.setEmail(employee.getEmail());

            Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
            return ResponseEntity.ok(updatedEmployee);
        }).orElseGet( ()-> ResponseEntity.notFound().build());
    }


    // delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Deleted the entity successfully");
    }

}
