package com.petfriendbackend.model.dto;

import lombok.Data;

@Data
public class UserFilterDto {
    private String role;

    private String location;
}
