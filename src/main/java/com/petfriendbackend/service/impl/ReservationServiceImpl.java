package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Reservation;
import com.petfriendbackend.model.Role;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.ConfirmationDto;
import com.petfriendbackend.model.dto.ReservationDto;
import com.petfriendbackend.model.exceptions.ReservationNotFoundException;
import com.petfriendbackend.repository.CategoryRepository;
import com.petfriendbackend.repository.ReservationRepository;
import com.petfriendbackend.service.ReservationService;
import com.petfriendbackend.service.RoleService;
import com.petfriendbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserService userService;

    private final CategoryRepository categoryRepository;

    private final RoleService roleService;

    @Override
    public Reservation getById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public Reservation create(ReservationDto reservationDto) {
        User petSitter = userService.findByFirstName(reservationDto.getSitter());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "petOwner";
        if (principal instanceof User) {
            username = ((User) principal).getUsername();
        }
        User petOwner = userService.findByUsername(username);

        Reservation reservation = Reservation.builder()
                .date(reservationDto.getDate())
                .petOwner(petOwner)
                .petSitter(petSitter)
                .build();

        return this.reservationRepository.save(reservation);
    }

    @Override
    public Reservation delete(Long id) {
        Reservation reservation = this.getById(id);
        this.reservationRepository.delete(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findByPetOwner() {
        User petOwner = userService.findByUsername("petOwner");
        return this.reservationRepository.findByPetOwner(petOwner);
    }

    @Override
    public List<Reservation> findByPetSitterAndConfirmation(Boolean confirmation) {
            User petOwner = userService.findByUsername("petFriend");
            return this.reservationRepository.findByPetSitterAndConfirmation(petOwner,confirmation);
    }

    @Override
    public void confirmReservation(ConfirmationDto confirmationDto) {
        Reservation reservation = this.getById(confirmationDto.getReservationId());

        if (reservation.getConfirmation().equals(false)) {
            this.delete(reservation.getId());
        }

        reservation.setConfirmation(confirmationDto.getConfirmation());
    }

//    @Override
//    public List<Reservation> findByPetSitterAndCategories(Long petSitterId, List<Long> categories) {
//        User user = this.userService.getById(petSitterId);
//        List<Category> categoryList = this.categoryRepository.findAllById(categories);
//        return this.reservationRepository.findAllByPetSitterAndCategoriesIn(user, Collections.singleton(categoryList));
//    }
}
