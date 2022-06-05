package com.petfriendbackend.model;

import com.petfriendbackend.model.enumerations.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class Pet {

    @Id
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    private String image;

    public Pet() {
    }

    public Pet(String name, Integer age, Gender gender, String description, String image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.image=image;
    }
}
