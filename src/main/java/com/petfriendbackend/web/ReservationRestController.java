package com.petfriendbackend.web;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Reservation;
import com.petfriendbackend.model.User;
import com.petfriendbackend.model.dto.ConfirmationDto;
import com.petfriendbackend.model.dto.ReservationDto;
import com.petfriendbackend.service.ReservationService;
import com.petfriendbackend.service.UserService;
import com.petfriendbackend.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    private final UserService userService;

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = this.reservationService.create(reservationDto);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/confirmation")
    public void confirmReservation(@RequestBody ConfirmationDto confirmationDto) {
        this.reservationService.confirmReservation(confirmationDto);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<Reservation>> findByUser(Principal principal) {
        User user = this.userService.findByUsername(principal.getName());
        List<Reservation> reservations = this.reservationService.findByUser(user);

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/byPetSitterAndCategories")
    public ResponseEntity<List<Reservation>> findByPetSitterAndCategories(@RequestBody ReservationDto reservationDto) {
        List<Reservation> reservations=this.reservationService.findByPetSitterAndCategories(reservationDto.getSitterId(),
                reservationDto.getCategories());
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
