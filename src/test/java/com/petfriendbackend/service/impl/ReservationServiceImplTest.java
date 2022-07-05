package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.*;
import com.petfriendbackend.repository.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    public static final Long RESERVATION_ID = 1L;
    public static final Boolean RESERVATION_CONFIRMATION = true;
    public static final LocalDate RESERVATION_DATE = LocalDate.now();

    public static final Long RESERVATION_SITTER_ID = 1L;
    public static final Long RESERVATION_OWNER_ID = 1L;

    public static final Long USER_OWNER_ID = 1L;
    public static final Long USER_SITTER_ID = 1L;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        Reservation reservation = getReservation();

        when(this.reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getById(RESERVATION_ID);

        assertEquals(RESERVATION_ID, result.getId());
        assertEquals(RESERVATION_CONFIRMATION, result.getConfirmation());
        assertEquals(RESERVATION_DATE, result.getDate());
    }

    private Reservation getReservation() {
        User petOwner = getPetOwner();
        User petSitter = getPetSitter();

        Reservation reservation = new Reservation();
        reservation.setId(RESERVATION_ID);
        reservation.setConfirmation(RESERVATION_CONFIRMATION);
        reservation.setDate(RESERVATION_DATE);
        reservation.setPetOwner(petOwner);
        reservation.setPetSitter(petSitter);

        return reservation;
    }

    private User getPetOwner() {
        User user = new User();
        user.setId(USER_OWNER_ID);

        return user;
    }

    private User getPetSitter() {
        User user = new User();
        user.setId(USER_SITTER_ID);

        return user;
    }
}
