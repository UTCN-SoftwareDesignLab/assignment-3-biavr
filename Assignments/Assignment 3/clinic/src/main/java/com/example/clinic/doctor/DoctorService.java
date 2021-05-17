package com.example.clinic.doctor;

import com.example.clinic.consultation.dto.ConsultationDTO;
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
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public List<DoctorDTO> findAll(){
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    private Doctor findById(Long id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    public DoctorDTO create(DoctorDTO doctorDTO){
        Doctor doctor = doctorMapper.formDto(doctorDTO);
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    public DoctorDTO edit(Long id, DoctorDTO doctorDTO){
        Doctor newDoctor = findById(id);
        newDoctor.setName(doctorDTO.getName());
        Doctor edited = doctorRepository.save(newDoctor);
        return doctorMapper.toDto(edited);
    }

    public void delete(Long id){
        doctorRepository.deleteById(id);
    }

    public DoctorDTO get(Long id){
        return doctorMapper.toDto(findById(id));
    }

}
