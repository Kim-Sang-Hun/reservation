package com.zerobase.member.controller;


import com.zerobase.member.domain.SignUpForm;
import com.zerobase.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl signUpServiceImpl;
    @RequestMapping(value = "/login")
    public String login() {

        return "member/login";
    }
    @GetMapping(value = "/signup")
    public String signUp() {

        return "member/signup";
    }

    @PostMapping(value = "/signup")
    public String signUpSubmit(SignUpForm signUpForm) {
        if (!signUpServiceImpl.signUp(signUpForm)) {
            return "member/signup_failure";
        }
        return "member/signup_complete";
    }

}