package com.zerobase.reservation.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservationForm {
    private String localDateTime;
    private Long storeId;
    private String username;
}