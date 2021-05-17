package com.example.clinic.notification;

import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.handler.annotation.*;

import static com.example.clinic.UrlMapping.DOCTORS;
import static com.example.clinic.UrlMapping.SECRETARIES;

@Controller
public class NotificationController {

    @MessageMapping(SECRETARIES)
    @SendTo(DOCTORS)
    public NewConsultationMessage newConsultation(ConsultationDetails details) throws Exception{
        Thread.sleep(1000);
        return new NewConsultationMessage("New appointment with "+ details.getPatient() + " at "+details.getDate());
    }
}
