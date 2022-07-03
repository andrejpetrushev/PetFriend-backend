package com.petfriendbackend.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationDto {
    private String sitter;

    private LocalDate date;
}
