package com.co.indra.coinmarketcap.users.controllers;

import com.co.indra.coinmarketcap.users.config.Routes;
import com.co.indra.coinmarketcap.users.model.MembershipType;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.MembershipTypeRepository;
import com.co.indra.coinmarketcap.users.repositories.UserRepository;
import com.co.indra.coinmarketcap.users.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // por cada test hace un rollback
public class MembershipControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private MembershipTypeRepository membershipTypeRepository;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   public void getMembership() throws Exception {
      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(Routes.MEMBERSHIP_PATH)
            .contentType(MediaType.APPLICATION_JSON);

      MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
      Assertions.assertEquals(200, response.getStatus());

      List<MembershipType> users = membershipTypeRepository.findAll();
      Assertions.assertEquals(3, users.size());
   }
}