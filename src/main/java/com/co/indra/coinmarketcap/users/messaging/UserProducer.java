package com.co.indra.coinmarketcap.users.messaging;

import com.co.indra.coinmarketcap.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void UserSender(User user){
        try {
            String msg= objectMapper.writeValueAsString(user);
            rabbitTemplate.convertAndSend("g3-users-ms", msg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
