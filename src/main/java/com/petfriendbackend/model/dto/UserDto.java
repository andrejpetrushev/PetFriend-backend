package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.User;
import com.petfriendbackend.model.Login;

public interface UserDto {
    void register(User user);

    User validateUser(Login login);
}
