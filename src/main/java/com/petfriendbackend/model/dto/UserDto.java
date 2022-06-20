package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.enumerations.Gender;
import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String password;

    private String repeatPassword;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String email;

    private String role;
}