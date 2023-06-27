package com.zerobase.member.controller;


import com.zerobase.member.domain.SignUpForm;
import com.zerobase.member.service.SignUpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final SignUpServiceImpl signUpServiceimpl;

    @GetMapping(value = "/signup")
    public String signUp() {

        return "member/signup";
    }

    @PostMapping(value = "/signup")
    public String signUpSubmit(HttpServletRequest request, HttpServletResponse response
            , SignUpForm signUpForm) {
        String username = signUpForm.getUsername();
        String password = signUpForm.getPassword();
        String memberType = signUpForm.getMemberType();

        if (memberType.equals("user")) {
            signUpServiceimpl.signUpUser(signUpForm);
        } else {
            signUpServiceimpl.signUpPartner(signUpForm);
        }

        return "member/signup_complete";
    }

}