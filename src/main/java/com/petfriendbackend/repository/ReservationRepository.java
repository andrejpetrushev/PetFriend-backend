package com.petfriendbackend.repository;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Reservation;
import com.petfriendbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPetOwner(User petOwner);

    List<Reservation> findByPetSitter(User petSitter);

    List<Reservation> findByPetOwnerAndPetSitter(User petOwner, User petSitter);

    List<Reservation> findAllByCategoriesAndPetSitter(List<Long> categories, User petSitter);
}
