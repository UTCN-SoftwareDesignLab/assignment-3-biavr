package com.example.clinic.consultation;

import com.example.clinic.consultation.dto.ConsultationDTO;
import com.example.clinic.consultation.mapper.ConsultationMapper;
import com.example.clinic.consultation.model.Consultation;
import com.example.clinic.doctor.dto.DoctorDTO;
import com.example.clinic.doctor.mapper.DoctorMapper;
import com.example.clinic.doctor.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final DoctorMapper doctorMapper;

    public List<ConsultationDTO> findAll(){
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    private Consultation findById(Long id){
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found"));
    }

    public ConsultationDTO create(ConsultationDTO consultationDTO){
        Consultation consultation = consultationMapper.fromDto(consultationDTO);
        return consultationMapper.toDto(consultationRepository.save(consultation));
    }

    public ConsultationDTO edit(Long id, ConsultationDTO consultation){
        Consultation newConsultation = findById(id);
        newConsultation.setDetails(consultation.getDetails());
        newConsultation.setDoctor(consultation.getDoctor());
        newConsultation.setPatient(consultation.getPatient());
        newConsultation.setTime(consultation.getTime());
        Consultation edited = consultationRepository.save(newConsultation);
        return consultationMapper.toDto(edited);
    }

    public void delete(Long id){
        consultationRepository.deleteById(id);
    }

    public List<ConsultationDTO> getDoctorConsultations(DoctorDTO doctorDTO){
        Doctor doctor = doctorMapper.formDto(doctorDTO);
        return consultationRepository.findAllByDoctor(doctor);
    }

    public ConsultationDTO get(Long id){
        return consultationMapper.toDto(findById(id));
    }
}
