package com.zerobase.reservation.service;

import com.zerobase.reservation.domain.ReservationForm;
import com.zerobase.reservation.domain.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ReservationService {
    default boolean uploadReservation(ReservationForm form) {return false;}
    default List<Reservation> checkReservationById(Long memberId) {return null;}
    default List<Reservation> checkReservationByStore(Long memberId, LocalDateTime localDateTime) {return null;}
    default void manageReservation(Long reservationId, String status) {}
    default boolean checkReservationValidity(Long reservationId) {return false;}
    default void validation(Long reservationId, boolean valid) {}
}