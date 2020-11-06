package com.example.camelsoaptolog.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JettyToLogRoute extends RouteBuilder {

    private final String URI = "jetty:http://localhost:9091/api/soapconsumer";

    @Override
    public void configure() throws Exception {
        from(URI)
                .routeId("from some jetty REST")
                .streamCaching("true")
                .log("${body}")
                .end();
    }
}
