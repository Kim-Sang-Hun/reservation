package com.zerobase.reservation.repository;

import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.store.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findByMemberIdOrderByReservationDateTimeDesc(Long memberId);
    List<Reservation> getByMemberIdOrderByReservationDateTimeDesc(Long memberId);
    
    
    Optional<List<Reservation>> findByStoreAndReservationDateTimeAfter(Store store, LocalDateTime localDateTime);
    List<Reservation> getByStoreAndReservationDateTimeAfter(Store store, LocalDateTime localDateTime);
}