package org.happiestminds.springboottesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.happiestminds.springboottesting.model.Employee;
import org.happiestminds.springboottesting.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc used for calling REST API.

    @MockBean
    private EmployeeService employeeService; // @MockBean annotation tell's that to create a mock instance of
                                             // EmployeeService and add it to the application context so
                                             // that it's injected into EmployeeController.
    @Autowired
    private ObjectMapper objectMapper;      // ObjectMapper Jackson class used to serialized and De-serialized
                                            // java object. help us to convert object into JSon.


    // Junit test for saveEmployee method.
    @DisplayName("Junit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee() throws Exception {
        // given- precondition or setup

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();
          // you have to mock saveEmployee() method , it's called stubbing
        BDDMockito.given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));    // its expect as a method argument./ it has only 1 argument that's why putted 0.

        // when- action or the behaviour that we are going to test (make call to the REST API)
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // then- verify the output or result using assert statement (response of the REST API)
        response.andDo(MockMvcResultHandlers.print()) // print()- this method help us to print response of the REST API
                .andExpect(MockMvcResultMatchers.status().isCreated()) // verify response is created or not.
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))  // verify Actual and Expected Json Value, $ is a Root Object , and Root Object is a Whole JSon Object.
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }


    // Junit test for getAllEmployee method.
    @DisplayName("Junit test for getAllEmployee method")
    @Test
    public void givenListOfEmployee_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
        // given- precondition or setup
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build());
        
        employeeList.add(Employee.builder()
                .firstName("Mithu")
                .lastName("Ahamed")
                .email("mithu@gmail.com")
                .build());
          // you have to mock getAllEmployee() method , it's called stubbing
        BDDMockito.given(employeeService.getAllEmployee()).willReturn(employeeList);

        // when- action or the behaviour that we are going to test(make call to the REST API)
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

        // then- verify the output or result using assert statement (response of the REST API)
        response.andExpect(MockMvcResultMatchers.status().isOk()) // response of the REST API
                .andDo(MockMvcResultHandlers.print())  //print response of the REST API
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(employeeList.size()))); // verify Actual and Expected Json Value

    }


    // Junit test for getEmployeeById method - (Positive Scenario- Valid employee Id)
    @DisplayName("Junit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        // given- precondition or setup
        long employeeId = 1l;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

           // you have to mock getEmployeeById() method its called Stubbing
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        // when- action or the behaviour that we are going to test(make call to the REST API)
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}",
                employeeId));

        // then- verify the output or result using assert statement
        response.andExpect(MockMvcResultMatchers.status().isOk()) // response of the REST API
                .andDo(MockMvcResultHandlers.print())            // print response of the rest api
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",CoreMatchers.is(employee.getEmail()))); // verify Actual and expected Json value.

    }


    // Negative scenario- Invalid Employee Id
    // Junit test for getEmployeeById method
    @DisplayName("Junit test for getEmployeeById method")
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
        // given- precondition or setup
        long employeeId = 1l;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

           // you have to mock getEmployeeById, its called stubbing
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        // when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));

        // then- verify the output or result using assert statement
        response.andExpect(MockMvcResultMatchers.status().isNotFound()) // response of the REST API
                .andDo(MockMvcResultHandlers.print()); // Print of response of the REST API

    }


    // Junit test for updateEmployee REST API- Positive scenario
    @DisplayName("Junit test for updateEmployee REST API")
    @Test
    public void givenUpdateEmployee_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() throws Exception {
        // given- precondition or setup

        long employeeId = 1L;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

        Employee updatedEmployee = Employee.builder()
                 .firstName("Mithu")
                 .lastName("Ahamed")
                 .email("mithu@gmail.com")
                 .build();

        // you have to mock getEmployeeById and updateEmployee, its called stubbing.
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
        BDDMockito.given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when- action or the behaviour that we are going to test, Make call to REST API.
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then- verify the output or result using assert statement
        response.andExpect(MockMvcResultMatchers.status().isOk()) // response of the REST API.
                .andDo(MockMvcResultHandlers.print()) // print the response of rest api.
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(updatedEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(updatedEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(updatedEmployee.getEmail()))); // verify the actual and expected Json

    }



    // Junit test for updateEmployee REST API - Negative scenario
    @DisplayName("Junit test for updateEmployee REST API - Negative scenario")
    @Test
    public void givenEmployeeIdNotExist_whenUpdateEmployee_thenReturnEmpty() throws Exception {
        // given- precondition or setup
        long employeeId = 1l;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

        Employee updatedEmployee = Employee.builder()
                .firstName("Mithu")
                .lastName("Ahamed")
                .email("mithu@gmail.com")
                .build();

            // you have to mock getEmployeeById and updateEmployee method, its called stubbing
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        BDDMockito.given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        // when- action or the behaviour that we are going to test, make call to REST API
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));


        // then- verify the output or result using assert statement
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }


    // Junit test for deleteEmployee REST API
    @DisplayName("Junit test for deleteEmployee REST API")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
        // given- precondition or setup
        long employeeId = 1l;

           // you have to mock deleteEmployee() method, its called stubbing
        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

        // when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId));

        // then- verify the output or result using assert statement
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}