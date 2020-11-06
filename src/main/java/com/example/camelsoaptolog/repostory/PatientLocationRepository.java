package com.example.camelsoaptolog.repostory;

import org.springframework.stereotype.Component;

@Component
public class PatientLocationRepository {
    public Integer getMoId(String omsNumber) {
        if (omsNumber.equals("5951997101536659")) {
            return 10;
        }
        return 0;
    }

    public Integer getDepId(String omsNumber) {
        if (omsNumber.equals("5951997101536659")) {
            return 20;
        }
        return 0;
    }
}
