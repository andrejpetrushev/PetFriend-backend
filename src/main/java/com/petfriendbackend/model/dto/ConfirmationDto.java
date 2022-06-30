package com.petfriendbackend.model.dto;

import lombok.Data;

@Data
public class ConfirmationDto {

    private Boolean confirmation;

    private Long reservationId;
}
