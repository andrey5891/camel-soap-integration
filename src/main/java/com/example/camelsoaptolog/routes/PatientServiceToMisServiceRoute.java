package com.example.camelsoaptolog.routes;

import com.example.camelsoaptolog.processor.CreateResponseProcessor;
import com.example.camelsoaptolog.processor.RequestConverterProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.mis.GetPatientLocationResponse;
import services.mis.MisService;
import services.patient.GetPatientRequest;
import services.patient.PatientService;

@Component
public class PatientServiceToMisServiceRoute extends RouteBuilder {
    private final String patientServiceURI =
            "cxf://http://localhost:8082/ws?serviceClass=" + PatientService.class.getName();

    private final String misServiceURI =
            "cxf://http://localhost:8083/ws?serviceClass=" + MisService.class.getName();

    @Autowired
    private RequestConverterProcessor requestConverterProcessor;

    @Autowired
    private CreateResponseProcessor createResponseProcessor;

    @Override
    public void configure() throws Exception {
        from(patientServiceURI)
                .routeId("PatientService >>> MisService")
                .convertBodyTo(GetPatientRequest.class)
                .log("patientId in request from PatientService ${body.patientId}")
                .process(requestConverterProcessor)
                .log("omsNumber in request to MisService ${body.omsNumber}")
                .to(misServiceURI)
                .convertBodyTo(GetPatientLocationResponse.class)
                .log("moId in response from MisService ${body.moId}")
                .process(createResponseProcessor)
                .log("moId in response to PatientService ${body.moId}")
                .end();
    }
}