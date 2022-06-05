package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.dto.PetDto;
import com.petfriendbackend.model.enumerations.Gender;
import com.petfriendbackend.model.exceptions.PetNotFoundException;
import com.petfriendbackend.repository.PetRepository;
import com.petfriendbackend.service.PetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> listPets() {
        return this.petRepository.findAll();
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return this.petRepository.findById(id);
    }

    @Override
    public Optional<Pet> findByName(String name) {
        return this.petRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Pet> save(String name, Integer age, Gender gender, String description, String image) {
        Pet pet=new Pet(name, age, gender, description, image);
        this.petRepository.save(pet);
        return Optional.of(pet);
    }

    @Override
    public Optional<Pet> save(PetDto petDto) {
        Pet pet=new Pet();

        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setGender(petDto.getGender());
        pet.setDescription(petDto.getDescription());
        pet.setImage(petDto.getImage());

        this.petRepository.save(pet);
        return Optional.of(pet);
    }

    @Override
    @Transactional
    public Optional<Pet> edit(Long id, String name, Integer age, Gender gender, String description, String image) {
        Pet pet=this.petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));

        pet.setName(name);
        pet.setAge(age);
        pet.setGender(gender);
        pet.setDescription(description);
        pet.setImage(image);

        this.petRepository.save(pet);
        return Optional.of(pet);
    }

    @Override
    public Optional<Pet> edit(Long id, PetDto petDto) {
        Pet pet=this.petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(id));

        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setGender(pet.getGender());
        pet.setDescription(petDto.getDescription());
        pet.setImage(petDto.getImage());

        this.petRepository.save(pet);

        return Optional.of(pet);
    }

    @Override
    public void deleteById(Long id) {
        this.petRepository.deleteById(id);
    }
}
