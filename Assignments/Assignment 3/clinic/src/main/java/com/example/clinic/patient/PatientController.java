package com.example.clinic.patient;

import com.example.clinic.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.clinic.UrlMapping.*;

@RestController
@RequestMapping(SECRETARIES + PATIENTS)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAll(){
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient){
        return patientService.create(patient);
    }

    @PutMapping(ENTITY)
    public PatientDTO edit(@PathVariable Long id, @RequestBody PatientDTO patient){
        return patientService.edit(id, patient);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }

    @GetMapping(ENTITY)
    public PatientDTO getPatient(@PathVariable Long id){
        return patientService.get(id);
    }
}
