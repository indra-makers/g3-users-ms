package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.config.ErrorCodes;
import com.co.indra.coinmarketcap.users.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.users.exceptions.NotFoundException;
import com.co.indra.coinmarketcap.users.messagingQueue.UsersProducer;
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
    @Autowired
    private UsersProducer usersProducer;


	@Cacheable(value = "user", cacheManager = "expire30Mins", key = "#idUser", unless = "#result == null")
	public User getUserCache(int idUser) {
		List<User> user = userRepository.findUserById(idUser);
		if (user.isEmpty()) {
			throw new NotFoundException(ErrorCodes.USER_NOT_FOUND);
		} else {
			return userRepository.findUserById(idUser).get(0);

		}
	}

    public void createUser(User user) {
        List<User> userByMail = userRepository.findByMail(user.getMail());
        if (!userByMail.isEmpty()) {
            throw new BusinessExceptions(ErrorCodes.USER);
        }
        usersProducer.sendUsers(user);
        userRepository.createUser(user);
    }

    public List<User> getUsers(){
        return userRepository.findUsers();
    }

    public User getUser(int idUser){
        if(userRepository.findUserById(idUser).isEmpty()){
            throw new NotFoundException(ErrorCodes.USER_NOT_FOUND.getMessage());
        }
        return userRepository.findUserById(idUser).get(0);
    }
}