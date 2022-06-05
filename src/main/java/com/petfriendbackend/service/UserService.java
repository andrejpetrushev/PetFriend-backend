package com.petfriendbackend.service;

import org.springframework.stereotype.Service;

import com.petfriendbackend.model.User;
import com.petfriendbackend.model.Login;

@Service
public interface UserService {

    void addUser(User user);

    User validateUser(Login login);

    void register(User user);
}