package com.petfriendbackend.service;

import com.petfriendbackend.model.Reservation;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.ConfirmationDto;
import com.petfriendbackend.model.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    Reservation getById(Long id);

    Reservation create(ReservationDto reservationDto);

    Reservation delete(Long id);

    List<Reservation> findByUser(User user);

    void confirmReservation(ConfirmationDto confirmationDto);
}