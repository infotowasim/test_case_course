//package org.happiestminds.springboottesting.integration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.CoreMatchers;
//import org.happiestminds.springboottesting.model.Employee;
//import org.happiestminds.springboottesting.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class EmployeeControllerIntegrationTests {
//
//    @Autowired
//    private MockMvc mockMvc; // Injecting MockMvc class to make HTTP request using perform() method.
//
//    @Autowired
//    private EmployeeRepository employeeRepository; // Injecting employee repository to use its methods to perform different operation on database.
//                                                   // it will be used for deleteAll method to delete the record from the Database table.
//
//    @Autowired
//    private ObjectMapper objectMapper; // used for serialization and De-serialization.
//
//    @BeforeEach
//    public void setup(){
//        employeeRepository.deleteAll();
//    }
//
//
//    // Junit test for saveEmployee REST API
//    @DisplayName("Junit test for saveEmployee REST API")
//    @Test
//    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee() throws Exception {
//        // given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        // when- action or the behaviour that we are going to test- make call to REST API
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(employee)));
//
//        // then- verify the output or result using assert statement
//        response.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(employee.getEmail())));
//
//
//    }
//
//
//    // Junit test for getAllEmployee REST API
//    @DisplayName("Junit test for getAllEmployee REST API")
//    @Test
//    public void givenListOfEmployee_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
//        // given- precondition or setup
//        List<Employee> employeeList = new ArrayList<>();
//
//        employeeList.add(Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build());
//
//        employeeList.add(Employee.builder()
//                .firstName("Mithu")
//                .lastName("Ahamed")
//                .email("mithu@gmail.com")
//                .build());
//
//        employeeRepository.saveAll(employeeList); //
//
//        // when- action or the behaviour that we are going to test- Make call to REST API
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));
//
//        // then- verify the output or result using assert statement
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
//                        CoreMatchers.is(employeeList.size())));
//    }
//
//
//    // Junit test for getEmployeeById REST API - POSITIVE Scenario
//    @DisplayName("Junit test for getEmployeeById REST API")
//    @Test
//    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
//        // given- precondition or setup
//        long employeeId = 1l;
//
//        Employee employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim2gmail.com")
//                .build();
//
//        employeeRepository.save(employee); //
//
//        // when- action or the behaviour that we are going to test- make a call to rest api
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getId())); //
//
//        // then- verify the output or result
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
//                        CoreMatchers.is(employee.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
//                        CoreMatchers.is(employee.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
//                        CoreMatchers.is(employee.getEmail())));
//    }
//
//
//
//    // Negative scenario- Invalid Employee Id
//    // Junit test for getEmployeeById method
//    @DisplayName("Junit test for getEmployeeById method")
//    @Test
//    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
//        // given- precondition or setup
//        long employeeId = 1l;
//
//        Employee employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        employeeRepository.save(employee); //
//
//        // when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));
//
//        // then- verify the output or result using assert statement
//        response.andExpect(MockMvcResultMatchers.status().isNotFound()) // response of the REST API
//                .andDo(MockMvcResultHandlers.print()); // Print of response of the REST API
//
//    }
//
//
//
//    // Junit test for updateEmployee REST API - Positive scenario
//    @DisplayName("Junit test for updateEmployee REST API")
//    @Test
//    public void givenUpdateEmployee_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() throws Exception {
//        // given- precondition or setup
//        long employeeId = 1l;
//
//        Employee savedEmployee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        employeeRepository.save(savedEmployee); //
//
//        Employee updatedEmployee = Employee.builder()
//                .firstName("Mithu")
//                .lastName("Ahamed")
//                .email("mithu@gmail.com")
//                .build();
//
//        // when- action or the behaviour that we are going to test - make a call to REST API
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", savedEmployee.getId()) //
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedEmployee)));
//
//        // then- verify the output
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
//                        CoreMatchers.is(updatedEmployee.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
//                        CoreMatchers.is(updatedEmployee.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
//                        CoreMatchers.is(updatedEmployee.getEmail())));
//
//
//    }
//
//
//
//    // Junit test for updateEmployee REST API - Negative scenario
//    @DisplayName("Junit test for updateEmployee REST API - Negative scenario")
//    @Test
//    public void givenEmployeeIdNotExist_whenUpdateEmployee_thenReturnEmpty() throws Exception {
//        // given- precondition or setup
//        long employeeId = 1l;
//
//        Employee savedEmployee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        employeeRepository.save(savedEmployee);
//
//        Employee updatedEmployee = Employee.builder()
//                .firstName("Mithu")
//                .lastName("Ahamed")
//                .email("mithu@gmail.com")
//                .build();
//
//
//        // when- action or the behaviour that we are going to test, make call to REST API
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedEmployee)));
//
//
//        // then- verify the output or result using assert statement
//        response.andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//
//    // Junit test for deleteEmployee REST API
//    @DisplayName("Junit test for deleteEmployee REST API")
//    @Test
//    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
//        // given- precondition or setup
//
//        Employee savedEmployee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        employeeRepository.save(savedEmployee);
//
//
//        // when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", savedEmployee.getId()));
//
//        // then- verify the output or result using assert statement
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//
//
//}
