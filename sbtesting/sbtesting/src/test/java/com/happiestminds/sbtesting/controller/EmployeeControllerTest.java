package com.happiestminds.sbtesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.happiestminds.sbtesting.entities.Employee;
import com.happiestminds.sbtesting.services.EmployeeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .firstName("Wasim")
                .lastName("Akram")
                .email("wasim@gmail.com")
                .build();
    }


    // Junit test for save employee REST API
    @DisplayName("Junit test for save employee REST API")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployeeObject() throws Exception {
        //given- precondition or setup

           // you have to mock saveEmployee method, its called stubbing
        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).willAnswer( (a)->a.getArgument(0));

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        //then- verify the output using ResultAction
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                        CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                        CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                        CoreMatchers.is(employee.getEmail())));
    }


        // Junit test for get all employee REST API
            @DisplayName("Junit test for get all employee REST API")
            @Test
            public void givenEmployeeList_whenGetAllEmployee_thenReturnEmployeeList() throws Exception {
                //given- precondition or setup

                List<Employee> employeeList = new ArrayList<>();

                employeeList.add(Employee.builder()
                        .firstName("Mithu")
                        .lastName("SK")
                        .email("mithu@gmail.com")
                        .build());

                   // you have to mock getAllEmployee method, its called stubbing.
                BDDMockito.given(employeeService.getAllEmployee()).willReturn(employeeList);

                //when- action or the behaviour that we are going to test
                ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

                //then- verify the output using AssertJ
                response.andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                                CoreMatchers.is(employeeList.size())));
            }



        // Junit test for get employee by id REST API- positive Scenario Valid employee Id
        @DisplayName("Junit test for get employee by id method")
        @Test
        public void givenValidEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception {
            //given- precondition or setup
            long employeeId = 1l;

               // you have to mock getEmployeeById method, its called stubbing
            BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

            //when- action or the behaviour that we are going to test
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));

            //then- verify the output using AssertJ
            response.andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                            CoreMatchers.is(employee.getFirstName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                            CoreMatchers.is(employee.getLastName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                            CoreMatchers.is(employee.getEmail())));
        }



       // Junit test for get Employee By Id REST API- negative scenario invalid employee id
        @DisplayName("Junit test for get Employee By Id- negative scenario invalid employee id")
        @Test
        public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception {
            //given- precondition or setup
            long employeeId = 1l;

               // you have to mock getEmployeeById method, its called stubbing
            BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

            //when- action or the behaviour that we are going to test
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId));

            //then- verify the output using AssertJ
            response.andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }


        // Junit test for update employee REST API- positive scenario
            @DisplayName("Junit test for update employee")
            @Test
            public void givenUpdateEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
                //given- precondition or setup

                long employeeId = 1l;

                Employee updateEmployee = Employee.builder()
                        .firstName("Mithu")
                        .lastName("SK")
                        .email("mithu@gmail.com")
                        .build();

                   // you have to mock getEmployeeById & updateEmployee method, its called stubbing.
                BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
                BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class)))
                        .willAnswer( (a)-> a.getArgument(0));


                //when- action or the behaviour that we are going to test
                ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateEmployee)));

                //then- verify the output using AssertJ
                response.andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",
                                CoreMatchers.is(updateEmployee.getFirstName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",
                                CoreMatchers.is(updateEmployee.getLastName())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.email",
                                CoreMatchers.is(updateEmployee.getEmail())));
            }


    // Junit test for update employee REST API- negative scenario not exist employee id
        @DisplayName("Junit test for update employee REST API- negative scenario not exist employee id")
        @Test
        public void givenNotExistEmployeeId_whenUpdateEmployee_thenReturnEmpty() throws Exception {
            //given- precondition or setup

            long employeeId = 1l;

            Employee updateEmployee = Employee.builder()
                    .firstName("Mithu")
                    .lastName("SK")
                    .email("mithu@gmail.com")
                    .build();

               // you have to mock getEmployeeById and updateEmployee method, its called stubbing.
            BDDMockito.given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
            BDDMockito.given(employeeService.updateEmployee(ArgumentMatchers.any(Employee.class)))
                    .willAnswer(a->a.getArgument(0));

            //when- action or the behaviour that we are going to test
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateEmployee)));

            //then- verify the output using AssertJ
            response.andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andDo(MockMvcResultHandlers.print());
        }



        // Junit test for delete employee REST API
    @DisplayName("Junit test for delete employee REST API")
    @Test
    public void givenEmployeeId_whenDeleteById_thenReturnNothing() throws Exception {
        // given- precondition or setup

        long employeeId = 1l;

           // you have to mock deleteEmployee method, its called stubbing.
        BDDMockito.willDoNothing().given(employeeService).deleteEmployee(employeeId);

        //when- action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/{id}", employeeId));

        //then- verify the output using
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }




}