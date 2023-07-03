package com.zerobase.review.service;

import com.zerobase.review.domain.ReviewForm;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    default boolean uploadReview(Long reservationId, ReviewForm form) {return false;}
}