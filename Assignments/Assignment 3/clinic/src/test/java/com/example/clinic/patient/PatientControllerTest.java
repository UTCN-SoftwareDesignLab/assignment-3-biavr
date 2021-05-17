package com.example.clinic.patient;

import com.example.clinic.BaseControllerTest;
import com.example.clinic.patient.dto.PatientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.example.clinic.TestCreationFactory.newPatientDTO;
import static com.example.clinic.TestCreationFactory.randomLong;
import static com.example.clinic.UrlMapping.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest extends BaseControllerTest {
    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService service;

    @BeforeEach
    void setup() {
        super.setUp();
        controller = new PatientController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        List<PatientDTO> patients = new ArrayList<>();
        int nrPatients = 10;
        for (int i = 0; i < nrPatients; i++) {
            PatientDTO patient = newPatientDTO();
            patients.add(patient);
        }

        when(service.findAll()).thenReturn(patients);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(SECRETARIES + PATIENTS));

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJsonContent = objectMapper.writeValueAsString(patients);
        result.andExpect(status().isOk())
                .andExpect(content().json(expectedJsonContent, true));
    }

    @Test
    void create() throws Exception {
        PatientDTO reqPatient = newPatientDTO();

        when(service.create(reqPatient)).thenReturn(reqPatient);

        ResultActions result = performPostWithRequestBody(SECRETARIES + PATIENTS, reqPatient);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }

    @Test
    void edit() throws Exception {
        long id = randomLong();
        PatientDTO reqPatient = newPatientDTO();

        when(service.edit(id, reqPatient)).thenReturn(reqPatient);

        ResultActions result = performPutWithRequestBodyAndPathVariable(SECRETARIES + PATIENTS + ENTITY, reqPatient, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();

        doNothing().when(service).delete(id);

        ResultActions result = performDeleteWithPathVariable(SECRETARIES + PATIENTS + ENTITY, id);
        result.andExpect(status().isOk());

        verify(service,times(1)).delete(id);
    }
}
