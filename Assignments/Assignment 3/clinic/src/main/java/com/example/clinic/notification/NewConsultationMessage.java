package com.example.clinic.notification;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class NewConsultationMessage {
    private String content;

}
