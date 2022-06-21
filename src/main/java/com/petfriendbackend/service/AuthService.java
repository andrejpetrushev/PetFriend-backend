package com.petfriendbackend.service;

import com.petfriendbackend.model.User;

public interface AuthService {

    User login(String username, String password);
}
