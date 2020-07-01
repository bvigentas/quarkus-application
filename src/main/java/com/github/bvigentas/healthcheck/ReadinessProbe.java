package com.github.bvigentas.healthcheck;

import com.github.bvigentas.rabbit.RabbitService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Readiness
public class ReadinessProbe implements HealthCheck {


    @Override
    public HealthCheckResponse call() {
        Client client = ClientBuilder.newClient();

        Response response = client.target("http://localhost:8085//rabbit-check").request().get();

        if (response.getStatus() == 200) {
            return HealthCheckResponse.up("Ready to go.");
        }
        return HealthCheckResponse.up("Not ready yet sir.");
    }
}
