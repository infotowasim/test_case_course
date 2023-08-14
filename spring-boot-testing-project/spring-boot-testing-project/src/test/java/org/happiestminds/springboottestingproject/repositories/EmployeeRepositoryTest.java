package org.happiestminds.springboottestingproject.repositories;

import static org.assertj.core.api.Assertions.*;
import org.happiestminds.springboottestingproject.entities.Employee;
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
    public void setup(){
        employee = Employee.builder()
                .firstName("Boni")
                .lastName("Amin")
                .email("boni@gmail.com")
                .build();
    }

    // Junit test for save employee operation
    @DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnEmployeeSavedObject() {
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();

        //when- action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.save(employee);

        //then- verify the output using assertJ statement
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    // Junit test for get all employee operation
    @DisplayName("Junit test for get all employee operation")
    @Test
    public void givenEmployeeListObject_whenFindAll_thenReturnEmployeeList() {
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();
//    }

        Employee employee2 = Employee.builder()
                .firstName("Virat")
                .lastName("Kohli")
                .email("virat@gmail.com")
                .build();

        Employee employee3 = Employee.builder()
                .firstName("Mohamad")
                .lastName("Shami")
                .email("mohamad@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when- action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then- verify the output using assertJ statement
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(3);

    }

    // Junit test for get employee by id operation
    @DisplayName("unit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeList(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee employee1 = employeeRepository.findById(employee.getId()).get();

        //then- verify the output using assertJ
        assertThat(employee1).isNotNull();


    }

    // Junit test for find by email operation
    @DisplayName("Junit test for find by email operation")
    @Test
    public void givenEmployeeObject_whenFindByEmail_thenReturnEmployeeObject(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee employee1 = employeeRepository.findByEmail(employee.getEmail()).get();

        //then- verify the output using assertJ
        assertThat(employee1).isNotNull();
    }

    // Junit test for update employee operation
    @DisplayName("unit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Yousuf");
        savedEmployee.setLastName("Youhana");
        savedEmployee.setEmail("yousuf@gmail.com");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then- verify the output using assertJ
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Yousuf");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Youhana");
        assertThat(updatedEmployee.getEmail()).isEqualTo("yousuf@gmail.com");
    }

    // Junit test for delete employee operation
    @DisplayName("Junit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();
//    }

        employeeRepository.save(employee);

        //when- action or the behaviour that we are going to test
//        employeeRepository.deleteById(employee.getId());
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        //then- verify the output using assertJ
        assertThat(employeeOptional).isEmpty();
    }

    // Junit test for custom query using JPQL with index parameter
    @DisplayName("Junit test for custom query using JPQL with index parameter")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();
        
        employeeRepository.save(employee);
        String firstName = "Boni";
        String lastName = "Amin";

        //when- action or the behaviour that we are going to test
        Employee savedEmployee1 = employeeRepository.findByJPQL(firstName, lastName);

        //then- verify the output using assertJ
        assertThat(savedEmployee1).isNotNull();
    }
    
    // Junit test for custom query using JPQL named parameter
    @DisplayName("Junit test for custom query using JPQL named parameter")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();
//    }
        
        employeeRepository.save(employee);
        
        String firstName = "Boni";
        String lastName = "Amin";
        
        //when- action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        //then- verify the output using assertJ
        assertThat(savedEmployee).isNotNull();
    }

    // Junit test for define custom query using native SQL with index parameter
    @DisplayName("Junit test for define custom query using native SQL with index parameter")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Rohit")
//                .lastName("Sharma")
//                .email("sharma@gmail.com")
//                .build();

        employeeRepository.save(employee);

//        String firstName = "Boni";
//        String lastName = "Amin";

        //when- action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        //then- verify the output using assertJ
        assertThat(savedEmployee).isNotNull();

    }

    // Junit test for define custom query using native SQL with named parameter
    @DisplayName("Junit test for define custom query using native SQL with named parameter")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Boni")
//                .lastName("Amin")
//                .email("boni@gmail.com")
//                .build();
//    }

        employeeRepository.save(employee);

//        String firstName = "Boni";
//        String lastName = "Amin";

        //when- action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(employee.getFirstName(), employee.getLastName());

        //then- verify the output
        assertThat(savedEmployee).isNotNull();
    }


}

