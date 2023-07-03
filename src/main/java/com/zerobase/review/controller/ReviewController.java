package com.zerobase.review.controller;

import com.zerobase.reservation.service.ReservationServiceImpl;
import com.zerobase.review.domain.ReviewForm;
import com.zerobase.review.service.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final ReservationServiceImpl reservationService;

    @GetMapping("/upload")
    private String uploadPage(@RequestParam Long reservationId, Model model) {
        if (reservationService.checkReservationValidity(reservationId)) {
            model.addAttribute("reservationId", reservationId);
            return "/review/upload";
        }
        return "/reservation/check_failure";
    }

    @PostMapping("/upload")
    private String uploadReview(@RequestParam Long reservationId, ReviewForm form) {
        reviewService.uploadReview(reservationId, form);
        return "/main/user";
    }
}