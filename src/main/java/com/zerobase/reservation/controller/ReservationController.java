package com.zerobase.reservation.controller;

import com.zerobase.member.service.MemberServiceImpl;
import com.zerobase.reservation.domain.ReservationForm;
import com.zerobase.reservation.service.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationServiceImpl reservationService;
    private final MemberServiceImpl memberService;

    @GetMapping(value = "/reservation")
    public String reservation(@RequestParam Long storeId, Model model) {
        model.addAttribute("storeId", storeId);
        return "reservation/reservation";
    }

    @PostMapping(value = "/reservation")
    public String reservationSubmit(
            ReservationForm form
    ) {
        if (!reservationService.uploadReservation(form)) {
            return "reservation/reservation_failure";
        }
        return "reservation/reservation_success";
    }

    @RequestMapping(value = "/check")
    public String checkReservation(Authentication authentication, Model model
    ) {
        Long userId = memberService.getUserId(authentication.getName());
        model.addAttribute("reservationList", reservationService.checkReservationById(userId));
        return "/reservation/check";
    }

    @RequestMapping(value = "/manage")
    public String manageReservation(Authentication authentication
            , Model model
    ) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Long userId = memberService.getUserId(authentication.getName());
        model.addAttribute("reservationList", reservationService.checkReservationByStore(userId, localDateTime));
        return "/reservation/manage";
    }

    @PostMapping(value = "/manage")
    public String manageReservationSubmit(
            @RequestParam Long reservationId,
            @RequestParam String manage
    ) {
        if (manage.equals("accept")) {
            reservationService.manageReservation(reservationId, "예약 승낙");
        } else if (manage.equals("reject")) {
            reservationService.manageReservation(reservationId, "예약 거절");
        }
        return "/main/partner";
    }
}