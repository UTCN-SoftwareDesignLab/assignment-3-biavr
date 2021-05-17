package com.example.clinic.consultation.mapper;

import com.example.clinic.consultation.dto.ConsultationDTO;
import com.example.clinic.consultation.model.Consultation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    ConsultationDTO toDto(Consultation consultation);

    Consultation fromDto(ConsultationDTO consultationDTO);
}
