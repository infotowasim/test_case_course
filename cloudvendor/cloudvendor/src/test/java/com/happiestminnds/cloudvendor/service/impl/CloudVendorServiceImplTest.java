package com.happiestminnds.cloudvendor.service.impl;

import com.happiestminnds.cloudvendor.model.CloudVendor;
import com.happiestminnds.cloudvendor.repository.CloudVendorRepository;
import com.happiestminnds.cloudvendor.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


class CloudVendorServiceImplTest {

    @Mock
    // you need to Instance of CloudVendorRepository
    private CloudVendorRepository cloudVendorRepository;
    //U need to Instance of CloudVendorService
    private CloudVendorService cloudVendorService;
    //U need to Instance of CloudVendor
    CloudVendor cloudVendor;
    //U need to Instance of AutoCloseable
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        //Instantiate CloudVendorService
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        //Initialize the CloudVendor Object here
        cloudVendor = new CloudVendor("1","AMAZON","USA","xxxxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        //Entire entity we will MOCK here
        mock(CloudVendor.class);
        //CloudVendorRepository class mock done here
        mock(CloudVendorRepository.class);
        // when-then Concepts.
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        //checking behaviours
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Success");
    }



    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }


    @Test
    void testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("AMAZON")).thenReturn(new ArrayList<>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getByVendorName("AMAZON").get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());

    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }



    @Test
    void deleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");


    }
}