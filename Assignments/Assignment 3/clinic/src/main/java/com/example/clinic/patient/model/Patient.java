package com.example.clinic.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 8, nullable = false)
    private String idCardNo;

    @Column(length = 10, nullable = false)
    private String cnp;

    @Column
    private Date birthDate;

    @Column(length = 256)
    private String address;
}
