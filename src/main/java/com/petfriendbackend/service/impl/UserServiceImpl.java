package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.UserDto;
import com.petfriendbackend.model.exceptions.UserDoNotExistsException;
import com.petfriendbackend.model.exceptions.UsernameAlreadyExistsException;
import com.petfriendbackend.repository.UserRepository;
import com.petfriendbackend.service.RoleService;
import com.petfriendbackend.service.UserService;
import com.petfriendbackend.service.forms.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(UserDoNotExistsException::new);
    }

    @Override
    public User register(UserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExistsException(userDto.getUsername());
        }
        Role role = this.roleService.getRoleByName("ROLE_USER");
        User user = new User(userDto.getUsername(),userDto.getFirstName(), userDto.getLastName(),
                userDto.getGender(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Collections.singleton(role));

        return this.userRepository.save(user);
    }

    @Override
    public User add(UserForm userForm) {
        User user = User.build(userForm.getUserName(),userForm.getFirstName(),userForm.getLastName(),userForm.getGender(),userForm.getEmail(),userForm.getPassword(),userForm.getRoles());
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(Long id, UserDto userDto) {
        User user = this.getById(id);
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();

        if (Objects.nonNull(firstName)) {
            user.setFirstName(firstName);
        }
        if (Objects.nonNull(lastName)) {
            user.setLastName(lastName);
        }

        return this.userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = this.getById(id);

        this.userRepository.delete(user);

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(UserDoNotExistsException::new);
    }
}