package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.config.ErrorCodes;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {

        List<User> userByMail = userRepository.findByUserMail(user.getMail());

        if (!userByMail.isEmpty()) {
            throw new BusinessException(ErrorCodes.USER);
        }

        userRepository.save(user);
    }
}
