package com.petfriendbackend.repository;

import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.enumerations.Gender;
import com.petfriendbackend.service.PetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetRepositoryTest {

    public static final Long PET_ID = 1L;
    public static final String PET_NAME = "Pet";
    public static final Integer PET_AGE = 5;
    public static final String PET_DESCRIPTION = "Description";
    public static final String PET_IMAGE = "image";
    public static final Gender PET_GENDER = Gender.FEMALE;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetService petService;

    @Before
    public void init() {
        if (petRepository.count() == 0) {
            Pet pet = getPet();
            petRepository.save(pet);
        }
    }

    @Test
    public void getPetByName() {
        Optional<Pet> pet = this.petRepository.findByName(PET_NAME);
        assertEquals(PET_NAME, pet.get().getName());
    }

    private Pet getPet() {
        Pet pet = new Pet();
        pet.setId(PET_ID);
        pet.setName(PET_NAME);
        pet.setAge(PET_AGE);
        pet.setGender(PET_GENDER);
        pet.setDescription(PET_DESCRIPTION);
        pet.setImage(PET_IMAGE);

        return pet;
    }

}