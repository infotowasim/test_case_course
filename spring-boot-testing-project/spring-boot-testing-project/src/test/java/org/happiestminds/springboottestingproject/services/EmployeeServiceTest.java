package org.happiestminds.springboottestingproject.services;


import org.assertj.core.api.Assertions;
import org.happiestminds.springboottestingproject.entities.Employee;
import org.happiestminds.springboottestingproject.exception.ResourceNotFoundException;
import org.happiestminds.springboottestingproject.repositories.EmployeeRepository;
import org.happiestminds.springboottestingproject.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Boni")
                .lastName("Amin")
                .email("boni@gmail.com")
                .build();
    }

    // Junit test for save employee method
    @DisplayName("Junit test for save employee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        //given- precondition or setup
          // create Employee object
//        Employee employee = Employee.builder()
//                .id(1L)
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();

            // We are going to stubbing, given() - willReturn()
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given( employeeRepository.save(employee)).willReturn(employee);

               // check employeeRepository object successfully mock or not
        System.out.println(employeeRepository);

             // check employeeService object successfully mock or not
        System.out.println(employeeService);


        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

            // check saveEmployee object successfully created or not
        System.out.println(saveEmployee);

        //then- verify the output using assertJ
        Assertions.assertThat(saveEmployee).isNotNull();
    }


    // Junit test for save employee method where throws exception
    @DisplayName("Junit test for save employee method where throws exception")
    @Test
    public void givenExistEmployeeEmail_whenSaveEmployee_thenReturnException(){
        //given- precondition or setup
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        //when- action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            employeeService.saveEmployee(employee);
        });

        //then- verify the output using assertJ
        verify(employeeRepository, never()).save(any(Employee.class));
    }


    // Junit test for get all employee object
    @DisplayName("Junit test for get all employee object")
    @Test
    public void givenListOfEmployee_whenGetAllEmployee_thenReturnEmployeeList(){
        //given- precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Rahul")
                .lastName("Amin")
                .build();

        Employee employee2 = Employee.builder()
                .id(3L)
                .firstName("Titu")
                .lastName("Ahamed")
                .email("titu@gmail.com")
                .build();

              // Stubbing, given()-willReturn()
        BDDMockito.given(employeeRepository.findAll()).willReturn(List.of(employee,employee1,employee2));

        //when- action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployee();

        //then- verify the output using assertJ
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(3);

    }

    // Junit test case for get all employee empty list(negative scenario)
    @DisplayName("Junit test case for get all employee empty list(negative scenario)")
    @Test
    public void givenEmptyEmployeeList_whenGetAllEmployee_thenReturnEmptyEmployeeList(){
        //given- precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Rahul")
                .lastName("Amin")
                .build();

        Employee employee2 = Employee.builder()
                .id(3L)
                .firstName("Titu")
                .lastName("Ahamed")
                .email("titu@gmail.com")
                .build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when- action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployee();

        //then- verify the output using assertJ
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);
    }

    // Junit test for get employee id method
    @DisplayName("Junit test for get employee id method ")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        //given- precondition or setup
        BDDMockito.given(employeeRepository.findById(1l)).willReturn(Optional.of(employee));

        //when- action or the behaviour that we are going to test
        Employee employee1 = employeeService.getEmployeeById(employee.getId()).get();

        //then- verify the output using assertJ
        Assertions.assertThat(employee1).isNotNull();

    }

    // Junit test for update employee method
    @DisplayName("Junit test for update employee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given- precondition or setup
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        employee.setFirstName("wasim");
        employee.setLastName("akram");
        employee.setEmail("wasim@gmail.com");

        //when- action or the behaviour that we are going to test
        Employee updateEmployee = employeeService.updateEmployee(employee);


        //then- verify the output using assertJ
        Assertions.assertThat(updateEmployee.getFirstName()).isEqualTo("wasim");
        Assertions.assertThat(updateEmployee.getLastName()).isEqualTo("akram");
        Assertions.assertThat(updateEmployee.getEmail()).isEqualTo("wasim@gmail.com");

    }

//    // Junit test for update employee method
//    @DisplayName("Junit test for update employee method")
//    @Test
//    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
//        //given- precondition or setup
//
//        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
//        employee.setFirstName("Mithu");
//        employee.setLastName("Akram");
//        employee.setEmail("mithu@gmail.com");
//
//        //when- action or the behaviour that we are going to test
//        Employee updateEmployee = employeeService.updateEmployee(employee);
//
//        //then- verify the output using assertJ
//        Assertions.assertThat(updateEmployee.getFirstName()).isEqualTo("Mithu");
//        Assertions.assertThat(updateEmployee.getLastName()).isEqualTo("Akram");
//        Assertions.assertThat(updateEmployee.getEmail()).isEqualTo("mithu@gmail.com");
//    }


    // Junit test for delete employee method
    @DisplayName("Junit test for delete employee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing(){
        //given- precondition or setup
        long employeeId = 1l;
    BDDMockito.willDoNothing().given(employeeRepository).deleteById(employeeId);

    //when- action or the behaviour that we are going to test
    employeeService.deleteEmployee(employeeId);

    //then- verify the output using assertJ
    verify(employeeRepository, times(1)).deleteById(employeeId);

    }


}