package com.example.clinic.doctor.mapper;

import com.example.clinic.doctor.dto.DoctorDTO;
import com.example.clinic.doctor.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDTO toDto(Doctor doctor);

    Doctor formDto(DoctorDTO doctorDTO);
}
