package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.User;
import com.petfriendbackend.model.exceptions.InvalidArgumentsException;
import com.petfriendbackend.model.exceptions.InvalidUserCredentialsException;
import com.petfriendbackend.repository.UserRepository;
import com.petfriendbackend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
