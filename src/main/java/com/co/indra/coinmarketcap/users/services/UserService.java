package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.config.ErrorCodes;
import com.co.indra.coinmarketcap.users.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.users.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.users.messaging.UserProducer;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProducer userProducer;

    public void createUser(User user) {

        List<User> userByMail = userRepository.findByMail(user.getMail());

        if (!userByMail.isEmpty()) {
            throw new BusinessExceptions(ErrorCodes.USER);
        }

        userProducer.sendUsers(user);
        userRepository.createUser(user);
    }
}