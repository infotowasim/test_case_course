package org.happiestminds.springboottestingproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.happiestminds.springboottestingproject.entities.Employee;
import org.happiestminds.springboottestingproject.services.EmployeeService;
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

@WebMvcTest
class EmployeeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private EmployeeService employeeService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
////    @BeforeEach
////    void setUp() {
////    }
//
//
//    // Junit test for saveEmployee
//    @DisplayName("Junit test for saveEmployee")
//    @Test
//    public void givenEmployeeObject_whenSveEmployee_thenReturnSavedEmployee() throws Exception {
//        //given- precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("wasim")
//                .lastName("akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).willAnswer( (a)->a.getArgument(0) );
//
//        //when- action or the behaviour that we sre going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(employee)));
//
//        //then- verify the output using assertJ
//        response.andExpect(MockMvcResultMatchers.status().isCreated())
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
//    // Junit test for Get All Employee
//    @DisplayName("Junit test for Get All Employee")
//    @Test
//    public void givenEmployeeList_whenGetAllEmployee_thenReturnEEmployeeList() throws Exception {
//        //given- precondition or setup
//
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
//                .lastName("SK")
//                .email("mithu@gmail.com")
//                .build());
//
//             // you should mock getAllEmployee method, its called stubbing.
//        BDDMockito.given(employeeService.getAllEmployee()).willReturn(employeeList);
//
//        //when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));
//
//        //then- verify the output using assertJ
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
//                        CoreMatchers.is(employeeList.size())));
//    }
//
//
//    // Junit test for Get Employee By Id
//    @DisplayName("Junit test for Get Employee By Id")
//    @Test
//    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
//        //given- precondition or setup
//
//        long employeeId = 1l;
//
//        Employee employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//           // you should mock the getEmployeeById method, its called stubbing
//        BDDMockito.given( employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
//
//        //when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));
//
//        //then- verify the output using assertJ
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
//                        CoreMatchers.is(employee.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
//                        CoreMatchers.is(employee.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
//                        CoreMatchers.is(employee.getEmail())));
//
//    }
//
//
//    // Junit test for update employee
//    @DisplayName("Junit test for update employee")
//    @Test
//    public void givenUpdateEmployee_whenUpdatedEmployee_thenReturnUpdatedEmployee() throws Exception {
//        // given- precondition or setup
//
//        long employeeId = 1l;
//
//        Employee employee = Employee.builder()
//                .firstName("Wasim")
//                .lastName("Akram")
//                .email("wasim@gmail.com")
//                .build();
//
//        Employee updatedEmployee = Employee.builder()
//                .firstName("Mithu")
//                .lastName("SK")
//                .email("mithu@gmail.com")
//                .build();
//
//           // you have to mock getEmployeeById method and updateEmployee method, its called stubbing.
//        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
//        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class))).willAnswer( (a) -> a.getArgument(0));
//
//        // when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedEmployee)));
//
//        // then- verify the output using assertJ
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
//                        CoreMatchers.is(updatedEmployee.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
//                        CoreMatchers.is(updatedEmployee.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
//                        CoreMatchers.is(updatedEmployee.getEmail())));
//    }
//
//
//    // Junit test for delete employee
//    @DisplayName("Junit test for delete employee")
//    @Test
//    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() throws Exception {
//        // given- precondition or setup
//        long employeeId = 1l;
//
//        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);
//
//        // when- action or the behaviour that we are going to test
//        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId));
//
//
//        // then- verify the output using assertJ
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;


    // Junit test for create employee
    @DisplayName("Junit test for create employee")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee() throws Exception {
        //given- precondition or setup

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

           // you have to mock saveEmployee method, its called stubbing
        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).willAnswer(a->a.getArgument(0));

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        //then- verify the output using assertJ
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }



    // Junit test for get all employee
    @DisplayName("Junit test for get all employee")
    @Test
    public void givenListEmployee_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
        //given- precondition or setup

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build());

        employeeList.add(Employee.builder()
                .firstName("Mithu")
                .lastName("SK")
                .email("mithu@gmail.com")
                .build());

           // you should mock getAllEmployee method, its called stubbing.
        BDDMockito.given(employeeService.getAllEmployee()).willReturn(employeeList);

        //when- action oe the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

        //then- verify the output using assertJ
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(employeeList.size())));
    }


    // Junit test for get Employee By Id
    @DisplayName("Junit test for get Employee By Id")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
        //given- precondition or setup

        long employeeId = 1l;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

           // you have to mock getEmployeeById method, its called stubbing.
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));

        //then- verify the output using assertJ
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }


    // Junit test for update employee
    @DisplayName("Junit test for update employee")
    @Test
    public void givenUpdateEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        //given- precondition or setup

        long employeeId = 1l;

        Employee employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();

        Employee UpdateEmployee = Employee.builder()
                .firstName("Mithu")
                .lastName("SK")
                .email("mithu@gmail.com")
                .build();

           // you have to mock getEmployeeById & updateEmployee method, its called stubbing.
        BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
        BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class))).willAnswer( (a)-> a.getArgument(0));

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}",employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateEmployee)));

        //then- verify the output using assertJ
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(UpdateEmployee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(UpdateEmployee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(UpdateEmployee.getEmail())));
    }


    // Junit test for delete employee
    @DisplayName("Junit test for delete employee")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() throws Exception {
        //given- precondition or setup

        long employeeId = 1l;

           // you have to mock delete employee method, its called stubbing
        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId));

        //then- verify the output using assertJ
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }






}