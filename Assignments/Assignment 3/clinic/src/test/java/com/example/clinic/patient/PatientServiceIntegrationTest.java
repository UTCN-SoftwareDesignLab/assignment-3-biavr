package com.example.clinic.patient;

import com.example.clinic.patient.dto.PatientDTO;
import com.example.clinic.patient.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PatientServiceIntegrationTest {

    @Autowired
    private PatientService service;

    @Autowired
    private PatientRepository repository;

    @Test
    void findAll(){
        int nrPatients = 10;
        for(int i = 0 ; i < nrPatients ; i++){
            repository.save(Patient.builder()
                    .id((long) i)
                    .name("Patient "+ Integer.toString(i))
                    .address("Address "+Integer.toString(i))
                    .cnp("XXXXXXXXXXXX"+Integer.toString(i))
                    .birthDate(new Date())
                    .idCardNo("XXXXXXX" + Integer.toString(i)).build());
        }

        List<PatientDTO> all = service.findAll();

        Assertions.assertEquals(nrPatients,all.size());
    }
}
