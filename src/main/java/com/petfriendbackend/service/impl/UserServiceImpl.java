package com.petfriendbackend.service.impl;

import com.petfriendbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petfriendbackend.model.Login;
import com.petfriendbackend.model.User;
import com.petfriendbackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepository userRepo;

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User validateUser(Login login) {
        return userRepo.findById(login.getUsername()).get();
    }

    @Override
    public void register(User user) {

    }
}

