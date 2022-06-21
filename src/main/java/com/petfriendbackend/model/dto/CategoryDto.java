package com.petfriendbackend.model.dto;

import com.petfriendbackend.model.Pet;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;

@Data
public class CategoryDto {

    private String name;

    @Column(length = 4000)
    private String description;

    private Set<Pet> pets;

    public CategoryDto () {

    }

    public CategoryDto(String name, String description, Set<Pet> pets) {
        this.name = name;
        this.description = description;
        this.pets = pets;
    }
}
