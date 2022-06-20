package com.petfriendbackend.service;


import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User register(UserDto userDto);

    User update(Long id, UserDto userDto);

    User delete(Long id);

    User findByUsername(String username);
}
