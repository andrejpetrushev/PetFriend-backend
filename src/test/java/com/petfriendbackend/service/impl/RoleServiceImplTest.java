package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoleServiceImplTest {

    public static final long ROLE_ID = 1L;
    public static final String ROLE_NAME = "ROLE";

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Role role = getRole();

        when(this.roleRepository.findById(ROLE_ID)).thenReturn(Optional.of(role));

        Role result = roleService.getRole(ROLE_ID);

        assertEquals(ROLE_ID, result.getId());
        assertEquals(ROLE_NAME, result.getName());
    }

    @Test
    public void testFindByName(){
        Role role = getRole();

        when(this.roleRepository.findByName(ROLE_NAME)).thenReturn(Optional.of(role));

        Role result = roleService.getRoleByName(ROLE_NAME);

        assertEquals(ROLE_ID, result.getId());
        assertEquals(ROLE_NAME, result.getName());
    }

    private Role getRole() {
        Role role = new Role();
        role.setId(ROLE_ID);
        role.setName(ROLE_NAME);

        return role;
    }
}
