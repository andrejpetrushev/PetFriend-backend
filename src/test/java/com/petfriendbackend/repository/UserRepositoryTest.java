package com.petfriendbackend.repository;

import com.petfriendbackend.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        if (userRepository.count() == 0) {
            User user = getUser();
            userRepository.save(user);
        }
    }

    @Test
    public void getUserByUsername() {
        Optional<User> user = this.userRepository.findByUsername(USERNAME);
        Boolean userExists = this.userRepository.existsByUsername(USERNAME);
        assertEquals(USERNAME, user.get().getUsername());
        assertTrue(userExists);
    }

    @Test
    public void getUserByUsernameAndPassword() {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(USERNAME, PASSWORD);
        assertEquals(USERNAME, user.get().getUsername());
        assertEquals(PASSWORD, user.get().getPassword());
    }


    private User getUser() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);

        return user;
    }

}