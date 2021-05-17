package com.example.clinic.consultation;

import com.example.clinic.consultation.dto.ConsultationDTO;
import com.example.clinic.consultation.model.Consultation;
import com.example.clinic.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    public List<ConsultationDTO> findAllByDoctor(Doctor doctor);
}
