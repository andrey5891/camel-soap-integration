package com.example.camelsoaptolog.processor;

import com.example.camelsoaptolog.repostory.PatientLocationRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.mis.GetPatientLocationRequest;
import services.mis.GetPatientLocationResponse;

@Component
public class MisServiceProcessor implements Processor {

    @Autowired
    PatientLocationRepository patientLocationRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        GetPatientLocationRequest request = exchange.getIn().getBody(GetPatientLocationRequest.class);
        String omsNumber = request.getOmsNumber();
        GetPatientLocationResponse response = new GetPatientLocationResponse();
        response.setMoId(patientLocationRepository.getMoId(omsNumber));
        response.setDepId(patientLocationRepository.getDepId(omsNumber));
        exchange.getIn().setBody(response);
    }
}