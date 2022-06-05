package com.petfriendbackend.service;

import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.dto.PetDto;
import com.petfriendbackend.model.enumerations.Gender;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> listPets();

    Optional<Pet> findById(Long id);

    Optional<Pet> findByName(String name);

    Optional<Pet> save(String name, Integer age, Gender gender, String description, String image);

    Optional<Pet> save(PetDto petDto);

    Optional<Pet> edit(Long id, String name, Integer age, Gender gender, String description, String image );

    Optional<Pet> edit(Long id, PetDto petDto);

    void deleteById(Long id);
}