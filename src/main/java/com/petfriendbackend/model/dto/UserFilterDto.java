package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserFilterDto {
    private Set<Role> roles;

    private String location;
}
