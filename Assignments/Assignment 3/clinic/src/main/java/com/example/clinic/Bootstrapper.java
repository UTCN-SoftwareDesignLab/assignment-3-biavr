package com.example.clinic;

import com.example.clinic.consultation.ConsultationRepository;
import com.example.clinic.consultation.ConsultationService;
import com.example.clinic.doctor.DoctorRepository;
import com.example.clinic.doctor.DoctorService;
import com.example.clinic.patient.PatientRepository;
import com.example.clinic.patient.PatientService;
import com.example.clinic.patient.dto.PatientDTO;
import com.example.clinic.security.AuthService;
import com.example.clinic.security.dto.SignupRequest;
import com.example.clinic.user.RoleRepository;
import com.example.clinic.user.UserRepository;
import com.example.clinic.user.model.ERole;
import com.example.clinic.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientRepository patientRepository;

    private final PatientService patientService;

    private final ConsultationRepository consultationRepository;

    private final ConsultationService consultationService;

    private final DoctorService doctorService;

    private final DoctorRepository doctorRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //patientRepository.deleteAll();
        if (bootstrap) {
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            doctorRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca@email.com")
                    .username("bianca")
                    .password("WooYoung!")
                    .roles(Set.of("DOCTOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("SECRETARY"))
                    .build());

            Date date = new GregorianCalendar(2002, Calendar.SEPTEMBER, 18).getTime();
            patientService.create(PatientDTO.builder()
                    .name("Nicolae Avram")
                    .cnp("5020918022468")
                    .birthDate(date)
                    .idCardNo("AX526663").build());
        }
    }
}
