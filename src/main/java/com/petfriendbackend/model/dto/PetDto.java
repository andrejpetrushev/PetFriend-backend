package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.enumerations.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class PetDto {

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    private String image;

    public PetDto() {
    }

    public PetDto(String name, Integer age, Gender gender, String description, String image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.image = image;
    }
}