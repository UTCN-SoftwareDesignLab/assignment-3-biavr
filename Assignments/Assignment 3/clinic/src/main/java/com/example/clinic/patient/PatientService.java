package com.example.clinic.patient;

import com.example.clinic.patient.PatientRepository;
import com.example.clinic.patient.dto.PatientDTO;
import com.example.clinic.patient.mapper.PatientMapper;
import com.example.clinic.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> findAll(){
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient){
        Patient patient1 = patientMapper.fromDto(patient);
        return patientMapper.toDto(patientRepository.save(patient1));
    }

    public PatientDTO edit(Long id, PatientDTO patient){
        Patient currentPatient = findById(id);
        currentPatient.setAddress(patient.getAddress());
        currentPatient.setCnp(patient.getCnp());
        currentPatient.setBirthDate(patient.getBirthDate());
        currentPatient.setName(patient.getName());
        currentPatient.setIdCardNo(patient.getIdCardNo());
        Patient patientEdited = patientRepository.save(currentPatient);
        return patientMapper.toDto(patientEdited);
    }

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found " + id));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }


    public PatientDTO get(Long id) {
        return patientMapper.toDto(findById(id));
    }
}
