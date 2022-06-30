package com.petfriendbackend.service;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.UserDto;
import com.petfriendbackend.service.forms.UserForm;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User register(UserDto userDto);

    User add(UserForm userForm);

    User update(Long id, UserDto userDto);

    User delete(Long id);

    User findByUsername(String username);

    User findAllByRoleAndLocation(Set<Role> roles, String location);
}
