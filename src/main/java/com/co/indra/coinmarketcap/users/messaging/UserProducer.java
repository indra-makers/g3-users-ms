package com.co.indra.coinmarketcap.users.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.indra.coinmarketcap.users.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserProducer {

   @Autowired
   private RabbitTemplate rabbitTemplate;

   @Autowired
   private ObjectMapper objectMapper;

   public void SendUser(User user) {
      
      SenderData senderData =  new SenderData(user.getUserId(),user.getMail(),user.getPhone());
      
      try {
         String message = objectMapper.writeValueAsString(senderData);
         System.out.println(message);
         rabbitTemplate.convertAndSend("user_notification_data", message);
      } catch (JsonProcessingException exc) {
         exc.printStackTrace();
      }

   }

}
