package com.example.clinic.consultation.dto;

import com.example.clinic.doctor.model.Doctor;
import com.example.clinic.patient.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private Date time;
    private String details;
    private Patient patient;
    private Doctor doctor;
}
