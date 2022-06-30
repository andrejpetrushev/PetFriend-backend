package com.petfriendbackend.service;


import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Role;

public interface RoleService {

   Role getRole(Long id);

   Role createRole(String name);

   Role getRoleByName(String name);

}
