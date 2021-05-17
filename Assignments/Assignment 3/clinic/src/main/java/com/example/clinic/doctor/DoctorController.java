package com.example.clinic.doctor;

import com.example.clinic.doctor.dto.DoctorDTO;
import com.example.clinic.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

import static com.example.clinic.UrlMapping.*;

@RestController
@RequestMapping(SECRETARIES + DOCTORS)
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> findAll(){
        return doctorService.findAll();
    }

    @PostMapping
    public DoctorDTO create(@RequestBody DoctorDTO doctor){
        return doctorService.create(doctor);
    }

    @PutMapping(ENTITY)
    public DoctorDTO edit(@PathVariable Long id, @RequestBody DoctorDTO doctor){
        return doctorService.edit(id, doctor);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        doctorService.delete(id);
    }

    @GetMapping(ENTITY)
    public DoctorDTO getDoctor(@PathVariable Long id){
        return doctorService.get(id);
    }
}
