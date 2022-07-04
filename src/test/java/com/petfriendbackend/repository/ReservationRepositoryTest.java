package com.petfriendbackend.repository;

import com.petfriendbackend.model.*;
import com.petfriendbackend.model.enumerations.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {

    public static final Long RESERVATION_ID = 1L;
    public static final Boolean RESERVATION_CONFIRMATION = true;
    public static final LocalDate RESERVATION_DATE = LocalDate.now();

    public static final Long USER_ID = 1L;
    public static final String USER_FIRST_NAME = "admin";
    public static final String USER_LAST_NAME = "admin";
    public static final Gender USER_GENDER = Gender.MALE;
    public static final String USER_USERNAME = "admin";
    public static final String USER_EMAIL = "admin@admin.com";
    public static final String USER_PASSWORD = "admin";

    public static final Long USER_2_ID = 2L;
    public static final String USER_2_FIRST_NAME = "user";
    public static final String USER_2_LAST_NAME = "user";
    public static final Gender USER_2_GENDER = Gender.FEMALE;
    public static final String USER_2_USERNAME = "user";
    public static final String USER_2_EMAIL = "user@user.com";
    public static final String USER_2_PASSWORD = "user";

    public static final long ROLE_ID = 1L;
    public static final String ROLE_NAME = "ADMIN";

    public static final long ROLE2_ID = 2L;
    public static final String ROLE2_NAME = "USER";

    public static final Long CATEGORY_ID = 1L;
    public static final String CATEGORY_NAME = "Cats";
    public static final String CATEGORY_DESCRIPTION = "Cats Category";

    public static final Long PET_ID = 1L;
    public static final int PET_AGE = 1;
    public static final String PET_DESCRIPTION = "Cat";
    public static final Gender PET_GENDER = Gender.FEMALE;
    public static final String PET_NAME = "Maya";


    public static final User user = new User();
    public static final User user2 = new User();

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void init() {
        Set<Role> roles = getRoles();

        List<Category> categories = getCategories();
        Collection<List<Category>> categoryCollection = new HashSet<>();
        categoryCollection.add(categories);

        if (this.userRepository.findAll().size() == 0) {
            user.setId(USER_ID);
            user.setFirstName(USER_FIRST_NAME);
            user.setLastName(USER_LAST_NAME);
            user.setGender(USER_GENDER);
            user.setUsername(USER_USERNAME);
            user.setEmail(USER_EMAIL);
            user.setPassword(USER_PASSWORD);
            user.setRoles(roles);

            user2.setId(USER_2_ID);
            user2.setFirstName(USER_2_FIRST_NAME);
            user2.setLastName(USER_2_LAST_NAME);
            user2.setGender(USER_2_GENDER);
            user2.setUsername(USER_2_USERNAME);
            user2.setEmail(USER_2_EMAIL);
            user2.setPassword(USER_2_PASSWORD);
            user2.setRoles(roles);

            this.userRepository.save(user);
            this.userRepository.save(user2);
        }

        if (this.reservationRepository.findAll().size() == 0) {
            Reservation reservation = getReservation();
            reservation.setPetOwner(user);
            reservation.setPetSitter(user2);

            this.reservationRepository.save(reservation);
        }
    }

    @Test
    public void testFindByPetOwner() {
        List<Reservation> reservations = this.reservationRepository.findByPetOwner(user);
        assertEquals(1, reservations.size());
    }

    @Test
    public void testFindByPetSitter() {
        List<Reservation> reservations = this.reservationRepository.findByPetSitter(user2);
        assertEquals(1, reservations.size());
    }

    private Reservation getReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(RESERVATION_ID);
        reservation.setConfirmation(RESERVATION_CONFIRMATION);
        reservation.setDate(RESERVATION_DATE);

        return reservation;
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

    private List<Category> getCategories() {
        Set<Pet> pets = getPets();

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setName(CATEGORY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);
        category.setPets(pets);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        return categories;
    }

    private Set<Pet> getPets() {
        Pet pet = new Pet();
        pet.setId(PET_ID);
        pet.setAge(PET_AGE);
        pet.setDescription(PET_DESCRIPTION);
        pet.setGender(PET_GENDER);
        pet.setName(PET_NAME);
        pet.setUser(user);

        Set<Pet> pets = new HashSet<>();
        pets.add(pet);

        return pets;
    }
}
