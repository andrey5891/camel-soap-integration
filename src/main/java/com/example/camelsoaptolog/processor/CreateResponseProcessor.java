package com.example.camelsoaptolog.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import services.mis.GetPatientLocationResponse;
import services.patient.GetPatientResponse;

@Component
public class CreateResponseProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        GetPatientLocationResponse getPatientLocationResponse = exchange.getIn().getBody(GetPatientLocationResponse.class);
        GetPatientResponse getPatientResponse = new GetPatientResponse();
        getPatientResponse.setMoId(getPatientLocationResponse.getMoId());
        getPatientResponse.setDepId(getPatientLocationResponse.getDepId());
        exchange.getIn().setBody(getPatientResponse);
    }
}
