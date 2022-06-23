package com.petfriendbackend.repository;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    public static final String ROLE_NAME = "ROLE";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Before
    public void init() {
        if (roleRepository.count() == 0) {
            Role role = getRole();
            roleRepository.save(role);
        }
    }

    @Test
    public void getRoleById() {
        Role role = this.roleService.getRoleByName(ROLE_NAME);
        assertEquals(ROLE_NAME, role.getName());
    }

    private Role getRole() {
        Role role = new Role();
        role.setName(ROLE_NAME);

        return role;
    }

}