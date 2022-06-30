package com.petfriendbackend.repository;


import com.petfriendbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petfriendbackend.model.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findAllByRolesAndLocation(Set<Role> roles, String location)

}