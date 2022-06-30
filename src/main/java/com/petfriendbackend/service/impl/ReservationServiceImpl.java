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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        User petOwner = userService.getById(reservationDto.getOwnerId());
        User petSitter = userService.getById(reservationDto.getSitterId());
        List<Category> categories = categoryRepository.findAllById(reservationDto.getCategories());

        Reservation reservation = Reservation.builder()
                .date(reservationDto.getDate())
                .petOwner(petOwner)
                .petSitter(petSitter)
                .categories(categories).build();

        return this.reservationRepository.save(reservation);
    }

    @Override
    public Reservation delete(Long id) {
        Reservation reservation = this.getById(id);
        this.reservationRepository.delete(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findByUser(User user) {
        Role role_pet_owner = this.roleService.getRoleByName("ROLE_PET_OWNER");

        if (user.getRoles().contains(role_pet_owner)) {
            return this.reservationRepository.findByPetOwner(user);
        }

        return this.reservationRepository.findByPetSitter(user);
    }

    @Override
    public void confirmReservation(ConfirmationDto confirmationDto) {
        Reservation reservation = this.getById(confirmationDto.getReservationId());

        if (reservation.getConfirmation().equals(false)) {
            this.delete(reservation.getId());
        }

        reservation.setConfirmation(confirmationDto.getConfirmation());
    }

    @Override
    public List<Reservation> findByPetSitterAndCategory(User petSitter, List<Category> categories) {
        Role role_pet_sitter = this.roleService.getRoleByName("ROLE_PET_SITTER");

        return this.reservationRepository.findByPetSitterAndCategory(petSitter, categories);
    }
}
