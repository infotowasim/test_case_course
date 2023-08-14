package com.happiestminds.sbtesting.repositories;

import com.happiestminds.sbtesting.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup(){
        employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();
    }



    // Junit test for employee by email operation
        @DisplayName("Junit test for employee by email operation")
        @Test
        public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
            //given- precondition or setup

            employeeRepository.save(employee);

            //when- action or the behaviour that we are going to test
            Employee saveEmployee = employeeRepository.findByEmail(employee.getEmail()).get();

            //then- verify the output using AssertJ
            Assertions.assertThat(saveEmployee).isNotNull();
        }




    // Junit test for custom query using JPQL index parameters
    @DisplayName("Junit test for custom query using JPQL index parameters")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        //given- precondition or setup

        employeeRepository.save(employee);

        String firstName = "Wasim";
        String lastName = "Akram";

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.findByJPQL(firstName, lastName);

        //then- verify the output using AssertJ
        Assertions.assertThat(saveEmployee).isNotNull();
    }



    // Junit test for custom query using JPQL named parameters
    @DisplayName("Junit test for custom query using JPQL named parameters")
    @Test
    public void givenEmployeeFirstNamedAndLastName_whenFindByJPQLNamedParam_thenReturnEmployeeObject(){
        //given- precondition or setup

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.findByJPQLNamedParam(employee.getFirstName(), employee.getLastName());

        //then- verify the output using AssertJ
        Assertions.assertThat(saveEmployee).isNotNull();
    }



    // Junit test for custom query SQL with index parameters
    @DisplayName("Junit test for custom query SQL with index parameters")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByNativeSQLIndexParam_thenReturnEmployeeObject(){
        //given- precondition or setup
        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.findByNativeSQLIndexParam(employee.getFirstName(), employee.getLastName());


        //then- verify the output using AssertJ
        Assertions.assertThat(saveEmployee).isNotNull();
    }


    // Junit test for native SQL query with named parameters
    @DisplayName("Junit test for native SQL query with named parameters")
    @Test
    public void givenEmployeeFirstNameAndLastName_whenFindByNativeSQLNamedParam_thenReturnEmployeeObject(){
        //given- precondition or setup

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.findByNativeSQLNamedParam(employee.getFirstName(), employee.getLastName());

        //then- verify the output using AssertJ
        Assertions.assertThat(saveEmployee).isNotNull();
    }

    
    
    
    

    // Junit test for save employee method
    @DisplayName("Junit test for save employee method")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnEmployeeSavedObject(){
        //given- precondition or setup



        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.save(employee);

        //then- verify the output using assertJ
        Assertions.assertThat(saveEmployee).isNotNull();
        Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);
    }


        // Junit test for get all employee
        @DisplayName("Junit test for get all employee")
        @Test
        public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){
            //given- precondition or setup

           Employee employee1 = Employee.builder()
                    .firstName("Mithu")
                    .lastName("SK")
                   .email("mithu@gmail.com")
                   .build();

           employeeRepository.save(employee);
           employeeRepository.save(employee1);


            //when- action or the behaviour that we are going to test
            List<Employee> employeeList = employeeRepository.findAll();

            //then- verify the output using AssertJ
            Assertions.assertThat(employeeList).isNotNull();
            Assertions.assertThat(employeeList.size()).isEqualTo(2);
        }



       // Junit test for employee by id operation
        @DisplayName("Junit test for employee by id operation")
        @Test
        public void givenEmployeeId_whenFindById_thenReturnEmployeeObject(){
        //given- precondition or setup

            employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
            Employee saveEmployee = employeeRepository.findById(employee.getId()).get();

            //then- verify the output using AssertJ
            Assertions.assertThat(saveEmployee).isNotNull();
        }



    // Junit test for update employee operation
        @DisplayName("Junit test for update employee operation")
        @Test
        public void givenEmployeeObject_whenUpdateEmployee_thenReturnEmployeeUpdatedObject(){
            //given- precondition or setup

            employeeRepository.save(employee);

            //when- action or the behaviour that we are going to test
            Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

            savedEmployee.setFirstName("Mithu");
            savedEmployee.setLastName("SK");
            savedEmployee.setEmail("mithu@gmail.com");

            Employee updatedEmployee = employeeRepository.save(savedEmployee);

            //then- verify the output using AssertJ
            Assertions.assertThat(updatedEmployee).isNotNull();
            Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("Mithu");
            Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("SK");
            Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("mithu@gmail.com");
        }



    // Junit test for delete employee operation
        @DisplayName("Junit test for delete employee operation")
        @Test
        public void givenEmployeeObject_whenDelete_thenRemoveEmployeeObject(){
            //given- precondition or setup

            employeeRepository.save(employee);

            //when- action or the behaviour that we are going to test
            employeeRepository.delete(employee);

            Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());

            //then- verify the output using AssertJ
            Assertions.assertThat(deletedEmployee).isEmpty();
        }

}