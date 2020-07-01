package com.github.bvigentas.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
public class RabbitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitService.class);
    
    protected Connection getConnection() {

        ConnectionFactory factory = new ConnectionFactory();

        final String rabbitHost = ConfigProvider.getConfig().getValue("rabbit.host", String.class);
        final String rabbitUsername = ConfigProvider.getConfig().getValue("rabbit.username", String.class);
        final String rabbitPassword = ConfigProvider.getConfig().getValue("rabbit.password", String.class);

        factory.setHost(rabbitHost);
        factory.setUsername(rabbitUsername);
        factory.setPassword(rabbitPassword);

        Connection connection = null;

        try {
            return factory.newConnection();
        } catch (TimeoutException | IOException e) {
            LOGGER.error("Erro ao conectar ao rabbit", e);
            throw new RuntimeException(e);
        }

    }

}
