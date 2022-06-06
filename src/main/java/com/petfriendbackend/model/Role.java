package com.petfriendbackend.model;

import javax.persistence.*;

@Table(name = "roles")
public class Role {

    private long id;

    private String name;

    public String getName() {
        return name;
    }
}
