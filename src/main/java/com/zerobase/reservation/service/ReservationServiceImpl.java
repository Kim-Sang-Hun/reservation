package com.zerobase.reservation.service;

import com.zerobase.member.domain.model.Member;
import com.zerobase.member.repository.MemberRepository;
import com.zerobase.reservation.domain.ReservationForm;
import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.store.domain.model.Store;
import com.zerobase.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    @Override
    public boolean uploadReservation(ReservationForm form) {
        Optional<Member> optionalMember = memberRepository.findByUsername(form.getUsername());
        if (optionalMember.isEmpty()) {
            return false;
        }
        Member member = memberRepository.getByUsername(form.getUsername());
        Store store = storeRepository.getById(form.getStoreId());
        Reservation reservation = Reservation.builder()
                .reservationDateTime(LocalDateTime
                        .parse(form.getLocalDateTime(), DateTimeFormatter.ISO_DATE_TIME))
                .member(member)
                .store(store)
                .status("예약 대기")
                .build();
        reservationRepository.save(reservation);

        return true;
    }

    @Override
    public List<Reservation> checkReservationById(Long memberId) {
        Optional<List<Reservation>> reservations = reservationRepository.findByMemberIdOrderByReservationDateTimeDesc(memberId);
        if (reservations.isEmpty()) {
            return null;
        }
        return reservationRepository.getByMemberIdOrderByReservationDateTimeDesc(memberId);
    }

    @Override
    public List<Reservation> checkReservationByStore(Long memberId, LocalDateTime localDateTime) {
        Store store = memberRepository.findById(memberId).orElseThrow(null).getStore();
        Optional<List<Reservation>> reservations = reservationRepository.findByStoreAndReservationDateTimeAfter(store, localDateTime);
        if (reservations.isEmpty()) {
            return null;
        }
        return reservationRepository.getByStoreAndReservationDateTimeAfter(store, localDateTime);
    }

    @Override
    public boolean checkReservationValidity(Long reservationId) {
        Reservation reservation = reservationRepository.getById(reservationId);
        if (!reservation.isValidation()) {
            return false;
        }
        return reservation.getReview() == null;
    }

    @Override
    public void manageReservation(Long reservationId, String status) {
        Reservation reservation = reservationRepository.getById(reservationId);
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

    @Override
    public void validation(Long reservationId, boolean valid) {
        Reservation reservation = reservationRepository.getById(reservationId);
        reservation.setValidation(valid);
        reservationRepository.save(reservation);
    }
}