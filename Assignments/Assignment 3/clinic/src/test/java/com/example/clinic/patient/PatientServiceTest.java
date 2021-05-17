package com.example.clinic.patient;

import com.example.clinic.AbsUnitTest;
import com.example.clinic.patient.dto.PatientDTO;
import com.example.clinic.patient.mapper.PatientMapper;
import com.example.clinic.patient.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.example.clinic.TestCreationFactory.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class PatientServiceTest extends AbsUnitTest {

    @InjectMocks
    private PatientService service;

    @Mock
    private PatientRepository repository;

    @Mock
    private PatientMapper mapper;

    @BeforeEach
    public void beforeEach(){
        super.setup();
        service = new PatientService(repository,mapper);
    }

    @Test
    void findAll(){
        List<Patient> patients = new ArrayList<>();
        int nrPatients = 10;
        for(int i = 0 ; i < nrPatients ; i++){
            patients.add(Patient.builder()
                    .id((long) i)
                    .name("Patient "+ Integer.toString(i))
                    .address("Address "+Integer.toString(i))
                    .cnp("XXXXXXXXXXXX"+Integer.toString(i))
                    .birthDate(new Date())
                    .idCardNo("XXXXXXX" + Integer.toString(i)).build());
        }

        when(repository.findAll()).thenReturn(patients);

        List<PatientDTO> all = service.findAll();

        verify(repository, times(1)).findAll();

        Assertions.assertEquals(patients.size(),all.size());
    }

    @Test
    void delete() {
        List<Patient> patients = new ArrayList<>();
        int nrPatients = 10;
        for(int i = 0 ; i < nrPatients ; i++){
            patients.add(Patient.builder()
                    .id((long) i)
                    .name("Patient "+ Integer.toString(i))
                    .address("Address "+Integer.toString(i))
                    .cnp("XXXXXXXXXXXX"+Integer.toString(i))
                    .birthDate(new Date())
                    .idCardNo("XXXXXXX" + Integer.toString(i)).build());
        }
        when(repository.findAll()).thenReturn(patients.subList(1,patients.size()));

        Long idToDelete = patients.get(0).getId();
        service.delete(idToDelete);

        verify(repository, times(1)).deleteById(any());

        List<PatientDTO> all = service.findAll();
        Assertions.assertEquals(nrPatients - 1,all.size());
    }

    @Test
    void create(){
        PatientDTO patient = newPatientDTO();
        Patient patient1 = mapper.fromDto(newPatientDTO());

        Patient result = repository.save(patient1);

        List<PatientDTO> all = service.findAll();
        Assertions.assertEquals(1,all.size());
    }
}
