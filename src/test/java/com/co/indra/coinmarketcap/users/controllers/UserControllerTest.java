package com.co.indra.coinmarketcap.users.controllers;

import com.co.indra.coinmarketcap.users.config.Routes;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.UserRepository;
import com.co.indra.coinmarketcap.users.responses.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.transaction.Transactional;


import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional  //por cada test hace un rollback
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    
    @Autowired
    private RedisConnection redisConnection;

    @BeforeEach
    public void beforeEach() {
        redisConnection.flushAll();
    }

    @MockBean
    private RabbitTemplate rabbitTemplate;


    @Test
    public void createUserHappyPath() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.USERS_PATH)
                .content("{\n" +
                        "    \"name\": \"julian\",\n" +
                        "    \"mail\": \"julian.giraldo2@utp.edu.co\",\n" +
                        "    \"phone\": \"+573147976714\",\n" +
                        "    \"idMembership\": 1\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<User> users = userRepository.findByMail("julian.giraldo2@utp.edu.co");
        Assertions.assertEquals(1, users.size());
    }

    @Test
    @Sql("/testdata/create_user.sql")
    public void createUserUserAlreadyExist() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.USERS_PATH)
                .content("{\n" +
                        "    \"name\": \"julian\",\n" +
                        "    \"mail\": \"julian.giraldo2@utp.edu.co\",\n" +
                        "    \"phone\": \"+573147976714\",\n" +
                        "    \"idMembership\": 1\n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);

        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("USER WITH THIS MAIL ALREADY EXIST", error.getMessage());
    }

    @Test
    @Sql("/testdata/create_user.sql")
    public void getUserById() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Routes.USERS_PATH+Routes.USER_PATH_PARAM,666).contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
        
        User users = objectMapper.readValue(response.getContentAsString(), User.class);
        Assertions.assertEquals("Test_username", users.getName());
    }

 
}
