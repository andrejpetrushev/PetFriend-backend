package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.enumerations.Gender;
import lombok.Data;

import java.util.List;

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

    private byte[] image;

    private String description;

    private String location;

    private String reservation;

    private List<Category> category;

    private double rating;
}
