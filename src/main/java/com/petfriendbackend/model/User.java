package com.petfriendbackend.model;

public class User {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String encrytedPassword;


    public User() {

    }

    public User(Long userId, String userName, String firstName, String lastName, String gender, String email, String encrytedPassword) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.encrytedPassword = encrytedPassword;
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEncrytedPassword() {

        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {

        this.encrytedPassword = encrytedPassword;
    }
}
