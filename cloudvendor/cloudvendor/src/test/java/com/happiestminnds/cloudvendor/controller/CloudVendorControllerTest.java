package com.happiestminnds.cloudvendor.controller;

import com.happiestminnds.cloudvendor.model.CloudVendor;
import com.happiestminnds.cloudvendor.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @MockBean
    private CloudVendorService cloudVendorService;
    private MockMvc mockMvc;

    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    List<CloudVendor> cloudVendorList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // Initialize 2 CloudVendor
        CloudVendor cloudVendorOne = new CloudVendor("1", "AMAZON", "USA", "xxxxx");
        CloudVendor cloudVendorTwo = new CloudVendor("2", "GCP", "UK", "yyyyy");

        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        // Defining Behaviours
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendorOne);
        //mocking the URL
        //If my URL gets invoked properly and does it sure then U should be returning the STATUS as OK.
        this.mockMvc.perform(get("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllCloudVendorDetails() {
    }

    @Test
    void createCloudVendorDetails() {
    }

    @Test
    void updateCloudVendorDetails() {
    }

    @Test
    void deleteCloudVendorDetails() {
    }
}