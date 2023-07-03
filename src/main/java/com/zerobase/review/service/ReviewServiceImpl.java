package com.zerobase.review.service;

import com.zerobase.reservation.domain.model.Reservation;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.review.domain.ReviewForm;
import com.zerobase.review.domain.model.Review;
import com.zerobase.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public boolean uploadReview(Long reservationId, ReviewForm form) {
        Reservation reservation = reservationRepository.getById(reservationId);
        Review review = Review.builder()
                .starRating(form.getStarRating())
                .reviewDescription(form.getReviewDescription())
                .build();
        reservation.setReview(review);
        review.setReservation(reservation);
        reviewRepository.save(review);
        reservationRepository.save(reservation);
        return true;
    }
}