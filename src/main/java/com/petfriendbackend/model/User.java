package com.petfriendbackend.model;

import com.petfriendbackend.model.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Role> roles;

    private byte[] image;

    private String description;

    public User(String username, String firstName, String lastName, Gender gender, String email, String password,
                Set<Role> roles, byte[] image, String description) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.image=image;
        this.description=description;
    }

    public static User build(String username, String firstName, String lastName, Gender gender, String email, String password,
                             Set<Role> roles, byte[] image, String description) {
        User user = new User();
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.gender = gender;
        user.email = email;
        user.password = password;
        user.roles = roles;
        user.image=image;
        user.description=description;
        return user;
    }
}