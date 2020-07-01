package com.github.bvigentas.rabbit;

import com.rabbitmq.client.Connection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rabbit-check")
public class RabbitHealthCheck {

    @Inject
    private RabbitService rabbitService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkRabbitConnection() {
        Connection connection = null;
        try {
            connection = this.rabbitService.getConnection();
        } catch (Exception e) {
            return "Não foi possível realizar a conexão com o rabbit.";
        }

        if (connection != null) {
            return "Conexão com rabbit realizada com sucesso!";
        }
        return "Não foi possível realizar a conexão com o rabbit.";
    }

}
