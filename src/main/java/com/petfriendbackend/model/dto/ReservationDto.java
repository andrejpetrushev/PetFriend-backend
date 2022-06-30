package com.petfriendbackend.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationDto {
    private Long sitterId;

    private Long ownerId;

    private List<Long> categories;

    private LocalDate date;
}
