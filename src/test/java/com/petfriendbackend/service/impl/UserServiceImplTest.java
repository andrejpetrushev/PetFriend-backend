package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.UserDto;
import com.petfriendbackend.model.enumerations.Gender;
import com.petfriendbackend.repository.RoleRepository;
import com.petfriendbackend.repository.UserRepository;
import com.petfriendbackend.service.forms.UserForm;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    public static final Long USER_ID = 1L;
    public static final String USER_FIRST_NAME = "user";
    public static final String USER_LAST_NAME = "user";
    public static final Gender USER_GENDER = Gender.MALE;
    public static final String USER_USERNAME = "user";
    public static final String USER_EMAIL = "user@user.com";
    public static final String USER_PASSWORD = "useruser";

    public static final long ROLE_ID = 1L;
    public static final String ROLE_NAME = "ADMIN";

    public static final long ROLE2_ID = 2L;
    public static final String ROLE2_NAME = "USER";

    public static final String USER_DTO_USERNAME = "admin";
    public static final String USER_DTO_FIRST_NAME = "admin";
    public static final String USER_DTO_LAST_NAME = "admin";
    public static final String USER_DTO_ROLE = "ADMIN";

    public static final String USER_FORM_USERNAME = "test";
    public static final String USER_FORM_FIRST_NAME = "test";
    public static final String USER_FORM_LAST_NAME = "test";
    public static final Gender USER_FORM_GENDER = Gender.FEMALE;
    public static final String USER_FORM_EMAIL = "test@test.com";
    public static final String USER_FORM_PASSWORD = "test";

    public static final Long ID = 2L;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void init() {
        Set<Role> roles = getRoles();

        if (this.userRepository.findAll().size() == 0) {
            User user = new User();
            user.setId(USER_ID);
            user.setFirstName(USER_FIRST_NAME);
            user.setLastName(USER_LAST_NAME);
            user.setGender(USER_GENDER);
            user.setUsername(USER_USERNAME);
            user.setEmail(USER_EMAIL);
            user.setPassword(USER_PASSWORD);
            user.setRoles(roles);

            this.userRepository.save(user);
        }
    }

    @Test
    public void testGetAll() {
        System.out.println(this.userRepository.findAll());
        List<User> userList = this.userService.getAll();
        assertEquals(1, userList.size());
    }

    @Test
    public void testGetById() {
        System.out.println(this.userRepository.findAll());
        User user = this.userService.getById(USER_ID);
        assertEquals(USER_ID, user.getId());
    }

    @Test
    public void testRegister() {
        System.out.println(this.userRepository.findAll());
        UserDto userDto = getUserDto();
        this.userService.register(userDto);

        assertEquals(2, this.userService.getAll().size());
    }

    @Test
    public void testUpdate() {
        System.out.println(this.userRepository.findAll());
        UserDto userDto = getUserDto();
        User user = this.userService.update(USER_ID, userDto);

        assertEquals(USER_ID, user.getId());
        assertEquals(USER_DTO_FIRST_NAME, user.getFirstName());
        assertEquals(USER_DTO_LAST_NAME, user.getLastName());
    }

    @Test
    public void testDelete() {
        this.userService.delete(ID);
        assertEquals(1, this.userService.getAll().size());
    }

    @Test
    public void testAdd() {
        UserForm userForm = getUserForm();
        User user = this.userService.add(userForm);

        assertEquals(USER_FORM_USERNAME, user.getUsername());
        assertEquals(USER_FORM_FIRST_NAME, user.getFirstName());
        assertEquals(USER_FORM_LAST_NAME, user.getLastName());
        assertEquals(USER_FORM_EMAIL, user.getEmail());
        assertEquals(USER_FORM_GENDER, user.getGender());
    }

    private Set<Role> getRoles() {
        Role role = new Role();
        role.setId(ROLE_ID);
        role.setName(ROLE_NAME);
        this.roleRepository.save(role);

        Role role2 = new Role();
        role2.setId(ROLE2_ID);
        role2.setName(ROLE2_NAME);
        this.roleRepository.save(role2);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        roles.add(role2);

        return roles;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername(USER_DTO_USERNAME);
        userDto.setFirstName(USER_DTO_FIRST_NAME);
        userDto.setLastName(USER_DTO_LAST_NAME);
        userDto.setEmail(USER_EMAIL);
        userDto.setPassword(USER_PASSWORD);
        userDto.setRepeatPassword(USER_PASSWORD);
        userDto.setRole(USER_DTO_ROLE);
        userDto.setGender(USER_GENDER);

        return userDto;
    }

    private UserForm getUserForm() {
        Set<Role> roles = getRoles();

        UserForm userForm = new UserForm();
        userForm.setUserName(USER_FORM_USERNAME);
        userForm.setFirstName(USER_FORM_FIRST_NAME);
        userForm.setLastName(USER_FORM_LAST_NAME);
        userForm.setEmail(USER_FORM_EMAIL);
        userForm.setPassword(USER_FORM_PASSWORD);
        userForm.setGender(USER_FORM_GENDER);
        userForm.setRoles(roles);

        return userForm;
    }

}
