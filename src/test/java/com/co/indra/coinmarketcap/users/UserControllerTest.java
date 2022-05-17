package com.co.indra.coinmarketcap.users;

import com.co.indra.coinmarketcap.users.config.Routes;
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
@Transactional  //por cada test hace un rollback
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Test
    public void createUserHappyPath() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.USERS_PATH)
                .content("{\n" +
                        "    \"name\":\"yosoyyo\",\n" +
                        "    \"mail\":\"yosoyyo2@gmail.com\",\n" +
                        "    \"idMembership\":\"2\"  \n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());

        List<User> users = userRepository.findByMail("yosoyyo2@gmail.com");
        Assertions.assertEquals(1, users.size());
    }

    @Test
    @Sql("/testdata/create_user.sql")
    public void createUserUserAlreadyExist() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(Routes.USERS_PATH)
                .content("{\n" +
                        "    \"name\":\"yosoyyo\",\n" +
                        "    \"mail\":\"test_mail\",\n" +
                        "    \"idMembership\":\"2\"  \n" +
                        "}").contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        Assertions.assertEquals(412, response.getStatus());

        String textResponse = response.getContentAsString();
        ErrorResponse error = objectMapper.readValue(textResponse, ErrorResponse.class);



        Assertions.assertEquals("001", error.getCode());
        Assertions.assertEquals("User with this mail already exist", error.getMessage());

    }
}
