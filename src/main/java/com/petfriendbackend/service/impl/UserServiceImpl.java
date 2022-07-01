package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.UserDto;

import com.petfriendbackend.model.enumerations.Gender;

import com.petfriendbackend.model.exceptions.InvalidArgumentsException;

import com.petfriendbackend.model.exceptions.UserDoNotExistsException;
import com.petfriendbackend.model.exceptions.UsernameAlreadyExistsException;
import com.petfriendbackend.repository.UserRepository;
import com.petfriendbackend.service.RoleService;
import com.petfriendbackend.service.UserService;
import com.petfriendbackend.service.forms.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        User user = new User(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getGender(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Collections.singleton(role), userDto.getImage(), userDto.getDescription(), userDto.getLocation(), userDto.getReservation(), userDto.getCategory(), userDto.getRating());

        return this.userRepository.save(user);
    }

    @Override
    public User add(UserForm userForm) {
        User user = User.build(userForm.getUserName(), userForm.getFirstName(), userForm.getLastName(), userForm.getGender(), userForm.getEmail(), userForm.getPassword(), userForm.getRoles(), userForm.getImage(), userForm.getDescription(), userForm.getLocation(), userForm.getReservation(), userForm.getCategories(), userForm.getRating());
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

    @Override
    public List<User> findAllByLocationAndRole(String location, String role) {
        Role role1=this.roleService.getRoleByName(role);
        List<User> users=this.userRepository.findAllByLocation(location);
        return users.stream().filter(x -> x.getRoles().contains(role1)).collect(Collectors.toList());
    }


    @Override
    public User addCategoryForPetSitter(Long id) {
        User user = new User();

        for (Category c : user.getCategories()) {
            if (id.equals(c.getId())) {
                for (Role r : user.getRoles()) {
                    if (r.getName().equals("PET_SITTER")) {
                        user.addCategory(c, id);
                    }
                }
            }
        }
        return user;
    }

    public double petSitterRating (Long id){
        User user1 = new User();
        User user2 = new User();
        double ratingsTotal = 0;
        double ratingsCount = 0;
        double userRating = 0;

        for (Role r1 : user1.getRoles()) {
            if (id.equals(user1.getId()) && r1.getName().equals("PET_OWNER")) {
                double rating = user1.getRating();
                for (Role r2 : user2.getRoles()) {
                    if (rating >= 1 && rating <= 5) {
                        if (r2.getName().equals("PET_SITTER")) {
                            user2.addRating(rating);
                            ratingsTotal += rating;
                            ratingsCount++;
                        }
                        userRating = ratingsTotal / ratingsCount;
                    }
                }
            }
        }
        return userRating;
    }
}
