package com.zerobase.store.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ReservationForm {
    private LocalDateTime localDateTime;
    private Long storeId;
    private String username;
}