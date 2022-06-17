package com.co.indra.coinmarketcap.users.messagingQueue;

import com.co.indra.coinmarketcap.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendUsers(User user) {
        try {
            String message = objectMapper.writeValueAsString(user);
            rabbitTemplate.convertAndSend("user_notification_data", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
