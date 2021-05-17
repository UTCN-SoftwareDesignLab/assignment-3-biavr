package com.example.clinic.consultation;

import com.example.clinic.consultation.dto.ConsultationDTO;
import com.example.clinic.doctor.dto.DoctorDTO;
import com.example.clinic.doctor.model.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.clinic.UrlMapping.*;

@RestController
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @GetMapping(SECRETARIES + CONSULTATIONS)
    public List<ConsultationDTO> findAll(){
        return consultationService.findAll();
    }

    @GetMapping(SECRETARIES + CONSULTATIONS + ENTITY)
    public ConsultationDTO getConsultation(@PathVariable Long id){
        return consultationService.get(id);
    }

    @PostMapping(SECRETARIES + CONSULTATIONS)
    public ConsultationDTO create(@RequestBody ConsultationDTO consultation){
        return consultationService.create(consultation);
    }

    @PatchMapping (SECRETARIES + CONSULTATIONS + ENTITY)
    public ConsultationDTO edit(@PathVariable Long id, @RequestBody ConsultationDTO consultation){
        return consultationService.edit(id,consultation);
    }

    @DeleteMapping(SECRETARIES + CONSULTATIONS + ENTITY)
    public void delete(@PathVariable Long id){
        consultationService.delete(id);
    }

    @GetMapping(DOCTORS + CONSULTATIONS)
    public List<ConsultationDTO> getDoctorConsultations(@RequestBody DoctorDTO doctorDTO){
        return consultationService.getDoctorConsultations(doctorDTO);
    }

    @PatchMapping (DOCTORS + CONSULTATIONS + ENTITY)
    public ConsultationDTO doctorEdit(@PathVariable Long id, @RequestBody ConsultationDTO consultation){
        return consultationService.edit(id,consultation);
    }
}
