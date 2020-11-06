package com.example.camelsoaptolog.routes;

import com.example.camelsoaptolog.processor.CreateResponseProcessor;
import com.example.camelsoaptolog.processor.MisServiceProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.mis.MisService;

@Component
public class ToMisRouteMock extends RouteBuilder {
    private final String misServiceURI =
            "cxf://http://localhost:8083/ws?serviceClass=" + MisService.class.getName();

    @Autowired
    private MisServiceProcessor misServiceProcessor;

    @Autowired
    private CreateResponseProcessor createResponseProcessor;

    @Override
    public void configure() throws Exception {
        from(misServiceURI)
                .routeId("MIS_ROUTE_MOCK")
                .process(misServiceProcessor)
                .end();
    }
}
