package com.petfriendbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petfriendbackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}