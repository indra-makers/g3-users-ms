package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.config.ErrorCodes;
import com.co.indra.coinmarketcap.users.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.users.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		List<User> userByMail = userRepository.findByMail(user.getMail());
		if (!userByMail.isEmpty()) {
			throw new BusinessExceptions(ErrorCodes.USER);
		}
		userRepository.createUser(user);
	}

	@Cacheable(value = "user", cacheManager = "expire30Mins", key = "#idUser", unless = "#result == null")
	public User getUsers(int idUser) {
		List<User> user = userRepository.findUserById(idUser);
		if (user.isEmpty()) {
			throw new NotFoundException(ErrorCodes.USER_NOT_FOUND);
		} else {
			return userRepository.findUserById(idUser).get(0);

		}

	}

}