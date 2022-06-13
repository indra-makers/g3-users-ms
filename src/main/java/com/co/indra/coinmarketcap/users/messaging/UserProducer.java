package com.co.indra.coinmarketcap.users.messaging;

import com.co.indra.coinmarketcap.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendUsers(User user) {
        try {
            String message = objectMapper.writeValueAsString(user);
            rabbitTemplate.convertAndSend("g3-users-ms", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

