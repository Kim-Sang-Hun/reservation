package com.zerobase.member.controller;


import com.zerobase.member.domain.SignUpForm;
import com.zerobase.member.repository.PartnerRepository;
import com.zerobase.member.repository.UserRepository;
import com.zerobase.member.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    private final UserRepository userRepository;

    private final PartnerRepository partnerRepository;

    @PostMapping(value = "/user")
    public void SignUpUser(Model model, @RequestBody SignUpForm userInfo) {

        System.out.println(userInfo.toString());
        boolean result = signUpService.signUpUser(userInfo);
        model.addAttribute("result", result);


    }

    @PostMapping(value = "/partner")
    public void SignUpPartner(@RequestParam String username, @RequestParam String password) {

    }

}