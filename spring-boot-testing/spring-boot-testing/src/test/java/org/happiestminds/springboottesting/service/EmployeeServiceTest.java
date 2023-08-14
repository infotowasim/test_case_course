package org.happiestminds.springboottesting.service;

import org.assertj.core.api.Assertions;
import org.happiestminds.springboottesting.exception.ResourceNotFoundException;
import org.happiestminds.springboottesting.model.Employee;
import org.happiestminds.springboottesting.repository.EmployeeRepository;
import org.happiestminds.springboottesting.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;  // import org.mockito.BDDMockito
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {




    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    // private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1l)
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();
    }


    // Junit test for saveEmployee method
    @DisplayName("Junit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        // given- precondition or setup
//        Employee employee = Employee.builder()
//                .id(1l)
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();



        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty()); // BDDMockito.
        given(employeeRepository.save(employee)).willReturn(employee); // BDDMockito.

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

        System.out.println(saveEmployee);

        // then- verify the output
        Assertions.assertThat(saveEmployee).isNotNull();
    }


    // Junit test for saveEmployee method which throws exception
    @DisplayName("Junit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
        // given- precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when- action oe the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            employeeService.saveEmployee(employee);
        });

        // then- verify the output
        verify(employeeRepository, never()).save(any(Employee.class));
    }


    // Junit test for getAllEmployee method.
    @DisplayName("Junit test for getAllEmployee method")
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){
        // given- precondition or setup
        Employee employee1 = Employee.builder()
                .id(2l)
                .firstName("Mithu")
                .lastName("Akram")
                .email("mithu@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // when- action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployee();

        // then- verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }


    // Junit test for getAllEmployee method empty (negative scenario).
    @DisplayName("Junit test for getAllEmployee method empty (negative scenario)")
    @Test
    public void givenEmptyEmployeeList_whenFindAllEmployee_thenReturnEmptyEmployeeList(){
        // given- precondition or setup.
        Employee employee1 = Employee.builder()
                .id(2l)
                .firstName("Mithu")
                .lastName("Akram")
                .email("mithu@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when- action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployee();

        // then- verify the output.
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);

    }


    // Junit test for getEmployeeById method.
    @DisplayName("Junit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given- precondition or setup
        given(employeeRepository.findById(1l)).willReturn(Optional.of(employee));

        // when- action or the behaviour that we are going to test
        Employee employee1 = employeeService.getEmployeeById(employee.getId()).get();

        // then- verify the output
        Assertions.assertThat(employee1).isNotNull();
    }


    // Junit test for updateEmployee method.
    @DisplayName("Junit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject(){
        // given- precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);

        employee.setFirstName("Wasim");
        employee.setEmail("wasim@gmail.com");

        // when- action or the behaviour that we are going to test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then- verify the output
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("Wasim");
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("wasim@gmail.com");
    }



    // Junit test for deleteEmployee method.
    @DisplayName("Junit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        // given- precondition or setup
        long employeeId = 1l;

        willDoNothing().given(employeeRepository).deleteById(employeeId); // method return type is void that's why we use BDDMockito.willDoNothing().

        // when- action or the behaviour that we are going to test
        employeeService.deleteEmployee(employeeId);

        // then- verify the output
        // How many times deleteById() methods call we will go for verifying
        verify(employeeRepository, times(1)).deleteById(employeeId);

    }
}