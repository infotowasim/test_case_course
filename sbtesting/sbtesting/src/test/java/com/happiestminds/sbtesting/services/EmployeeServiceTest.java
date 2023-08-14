package com.happiestminds.sbtesting.services;

import com.happiestminds.sbtesting.entities.Employee;
import com.happiestminds.sbtesting.exception.ResourseNotFoundException;
import com.happiestminds.sbtesting.repositories.EmployeeRepository;
import com.happiestminds.sbtesting.services.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();
    }


    // Junit test for save employee method
    @DisplayName("Junit test for save employee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee() {
        //given- precondition or setup

           // you have to mock saveEmployee method
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeService.saveEmployee(employee);

        //then- verify the output using
        Assertions.assertThat(saveEmployee).isNotNull();
    }



    // Junit test for save employee method when email already exist
    @DisplayName("Junit test for save employee method when email already exist")
    @Test
    public void givenAlreadyExistEmail_whenSaveEmployee_thenThrowsException(){
        //given- precondition or setup

               // you have to mock saveEmployee method
        BDDMockito.given( employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));

        //when- action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourseNotFoundException.class, ()->{
            employeeService.saveEmployee(employee);
        });

        //then- verify the output
        BDDMockito.verify(employeeRepository, Mockito.never()).save(ArgumentMatchers.any(Employee.class));
    }





    // Junit test for get all employee method
    @DisplayName("Junit test for get all employee method")
    @Test
    public void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeList(){
        //given- precondition or setup

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(employee);

        employeeList.add(Employee.builder()
                .firstName("Mithu")
                .lastName("SK")
                .email("mithu@gmail.com")
                .build());

               // you have to mock get all employee method, its called stubbing
        BDDMockito.given(employeeRepository.findAll()).willReturn(employeeList);

        //when- action or the behaviour that we are going to test
        List<Employee> employeeList1 = employeeService.getAllEmployee();

        //then- verify the output using AssertJ
        Assertions.assertThat(employeeList1).isNotNull();
        Assertions.assertThat(employeeList1.size()).isEqualTo(2);
    }


    // Junit test for get employee by id method
        @DisplayName("Junit test for get employee by id method")
        @Test
        public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
            //given- precondition or setup
            long employeeId = 1l;

                BDDMockito.given(employeeRepository.findById(employeeId)).willReturn(Optional.of(employee));

            //when- action or the behaviour that we are going to test
            Employee employee1 = employeeService.getEmployeeById(employeeId).get();

            //then- verify the output using AssertJ
            Assertions.assertThat(employee1).isNotNull();
        }

}