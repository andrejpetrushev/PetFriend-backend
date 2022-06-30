package com.petfriendbackend.service.forms;


import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.enumerations.Gender;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
public class UserForm {

    @Valid
    @NotEmpty
    private String userName;

    @Valid
    @NotEmpty
    private String firstName;

    @Valid
    @NotEmpty
    private String lastName;

    @Valid
    @NotEmpty
    private Gender gender;

    @Valid
    @NotEmpty
    private String email;

    @Valid
    @NotEmpty
    private String password;

    @Valid
    @NotEmpty
    private Set<Role> roles;

    @Valid
    @NotEmpty
    private byte[] image;

    @Valid
    @NotEmpty
    private String description;

    @Valid
    @NotEmpty
    private String location;

    @Valid
    @NotEmpty
    private String reservation;

    @Valid
    @NotEmpty
    private List<Category> categories;

    @Valid
    @NotEmpty
    private double rating;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getDescription() {return description; }

    public byte[] getImage() { return image; }

    public String getLocation() { return location; }

    public String getReservation() { return reservation; }

    public List<Category> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }
}
