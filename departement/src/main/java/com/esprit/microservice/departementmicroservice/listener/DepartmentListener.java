package com.esprit.microservice.departementmicroservice.listener;

import com.esprit.microservice.departementmicroservice.config.RabbitMQConfig;
import com.esprit.microservice.departementmicroservice.entities.Departement;
import com.esprit.microservice.departementmicroservice.repositories.DepartementRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class DepartmentListener {
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.REQUEST_QUEUE)
    @SendTo // This will automatically send back the response

    public String retrieveAllDepartements() throws Exception {
        var departments = departementRepository.findAll();
        return objectMapper.writeValueAsString(departments); // Return as JSON string
    }
}