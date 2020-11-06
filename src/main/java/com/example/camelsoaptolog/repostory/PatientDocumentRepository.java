package com.example.camelsoaptolog.repostory;

import org.springframework.stereotype.Component;

@Component
public class PatientDocumentRepository {
    public String getOmsNumberByPatientId(Integer patientId) {
        if (patientId.equals(12345)) {
            return "5951997101536659";
        }
        return "";
    }
}
