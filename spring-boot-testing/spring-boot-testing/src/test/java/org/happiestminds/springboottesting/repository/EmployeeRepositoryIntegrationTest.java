//package org.happiestminds.springboottesting.repository;
//
//import org.assertj.core.api.Assertions;
//import org.happiestminds.springboottesting.model.Employee;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // This annotation help us to disable In-memory database support(H2).
//class EmployeeRepositoryIntegrationTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    private Employee employee;
//
//    @BeforeEach
//    public void setup(){
//        employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//    }
//
//
//    //Junit test for save employee operation
//    @DisplayName("Junit test for save employee operation")
//    @Test
//    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
//
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//
//        // when- action or the behaviour that we are going test
//        Employee saveEmployee = employeeRepository.save(employee);
//
//
//        // then- verify the output
//        Assertions.assertThat(saveEmployee).isNotNull();
//        Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);
//
//    }
//
//
//
//    // Junit test for get all employee operation
//    @DisplayName("Junit test for get all employee operation")
//    @Test
//    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){
//        // given- precondition or setUp
//
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//
//        Employee employee1 = Employee.builder()
//                .firstName("Rahul")
//                .lastName("Amin")
//                .email("rahul@gmail.com")
//                .build();
//
//        employeeRepository.save(employee);
//        employeeRepository.save(employee1);
//
//        // when- action or the behaviour that we are going to test
//        List<Employee> employeeList = employeeRepository.findAll();
//
//        // then- verify the output
//        Assertions.assertThat(employeeList).isNotNull();
//        Assertions.assertThat(employeeList.size()).isEqualTo(3);
//
//    }
//
//
//    // Junit test for get employee by id operation
//    @DisplayName("Junit test for get employee by id operation")
//    @Test
//    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        // when- action or the behaviour that we are going to test
//        Employee employeeDB = employeeRepository.findById(employee.getId()).get();
//
//        // then- verify the output
//        Assertions.assertThat(employeeDB).isNotNull();
//    }
//
//
//    // Junit test for get employee by email operation
//    @DisplayName("Junit test for get employee by email operation")
//    @Test
//    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        // when- action or the behaviour that we are going to test
//        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();
//
//        // then- verify the output
//        Assertions.assertThat(employeeDB).isNotNull();
//    }
//
//
//    // Junit test for update employee operation
//    @DisplayName("Junit test for update employee operation")
//    @Test
//    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        // when- action or the behaviour that we are going to test
//        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
//
//        savedEmployee.setFirstName("Mithu");
//        savedEmployee.setLastName("SK");
//        savedEmployee.setEmail("mithu@gmail.com");
//
//        Employee updatedEmployee = employeeRepository.save(savedEmployee);
//
//        // then- verify the output
//        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("Mithu");
//        Assertions.assertThat(updatedEmployee.getLastName()).isEqualTo("SK");
//        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("mithu@gmail.com");
//
//    }
//
//
//    // Junit test for delete employee operation
//    @DisplayName("Junit test for delete employee operation")
//    @Test
//    public void givenEmployeeObject_whenDelete_thenRemoveEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        // when- action or the behaviour that we are going to test
//        employeeRepository.delete(employee);
//        //employeeRepository.deleteById(employee.getId()); // u can use any of these 2
//        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
//
//        // then- verify the output
//        Assertions.assertThat(optionalEmployee).isEmpty();
//    }
//
//
//    // Junit test for custom query using JPQL index
//    @DisplayName("Junit test for custom query using JPQL index")
//    @Test
//    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        String firstName = "Wasim";
//        String lastName = "Akram";
//
//        // when- action or the behaviour that we are going to test
//        Employee saveEmployee = employeeRepository.findByJPQL(firstName, lastName);
//
//        // then- verify the output
//        Assertions.assertThat(saveEmployee).isNotNull();
//
//    }
//
//
//    // Junit test for custom query using JPQL named param.
//    @DisplayName("Junit test for custom query using JPQL using Named param")
//    @Test
//    public void givenFirstNameAndLastName_whenFindByJPQLNamedParam_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        String firstName = "Wasim";
//        String lastName = "Akram";
//
//        // when- action or the behaviour that we are going to test
//        Employee saveEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);
//
//        // then- verify the output
//        Assertions.assertThat(saveEmployee).isNotNull();
//    }
//
//
////    // Junit test for custom query using native SQL index
//    @DisplayName("Junit test for custom query using native SQL index")
//    @Test
//    public void givenEmployeeFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
//        //    String firstName = "Wasim";
//        //    String lastName = "Akram";
//
//        // when- action or the behaviour that we are going to test
//        Employee saveEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());
//
//        // then- verify the output
//        Assertions.assertThat(saveEmployee).isNotNull();
//    }
//
//
//    // Junit test for custom Query using native SQL with named parameters.
//    @DisplayName("Junit test for custom Query using native SQL with named parameters")
//    @Test
//    public void givenEmployeeFirstNameAndLastName_whenFindByNativeSQLNamed_thenReturnEmployeeObject(){
//        // given- precondition or setUp
////        Employee employee = Employee.builder()
////                .firstName("Wasim")
////                .lastName("Akram")
////                .email("wasim@gmail.com")
////                .build();
//
//        employeeRepository.save(employee);
//
////        String firstName = "Wasim";
////        String lastName = "Akram";
//
//        // when- action or the behaviour that we are going to test
//        Employee saveEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(),employee.getLastName());
//
//        // then- verify the output
//        Assertions.assertThat(saveEmployee).isNotNull();
//    }
//
//}