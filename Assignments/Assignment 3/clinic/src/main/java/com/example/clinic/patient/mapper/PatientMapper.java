package com.example.clinic.patient.mapper;

import com.example.clinic.patient.dto.PatientDTO;
import com.example.clinic.patient.model.Patient;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);

    Patient fromDto(PatientDTO patientDTO);
}

