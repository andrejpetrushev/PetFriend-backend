package com.petfriendbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 4000)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pet> pets;

    public Category () {

    }

    public Category(String name, String description, Set<Pet> pets) {
        this.name = name;
        this.description = description;
        this.pets = pets;
    }
}
