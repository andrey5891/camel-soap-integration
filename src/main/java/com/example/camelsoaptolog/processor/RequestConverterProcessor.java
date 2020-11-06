package com.example.camelsoaptolog.processor;

import com.example.camelsoaptolog.repostory.PatientDocumentRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.mis.GetPatientLocationRequest;
import services.patient.GetPatientRequest;

@Component
public class RequestConverterProcessor implements Processor {

    @Autowired
    PatientDocumentRepository patientDocumentRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        GetPatientRequest getPatientRequest = exchange.getIn().getBody(GetPatientRequest.class);
        GetPatientLocationRequest getPatientLocationRequest = new GetPatientLocationRequest();

        getPatientLocationRequest.setOmsNumber(
                patientDocumentRepository.getOmsNumberByPatientId(getPatientRequest.getPatientId()));

        exchange.getIn().setBody(getPatientLocationRequest);
        exchange.getIn().setHeader(CxfConstants.OPERATION_NAME, "getPatientLocation");
        exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE, "http://mis.services/");
    }
}
