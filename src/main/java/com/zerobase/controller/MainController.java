package com.zerobase.controller;

import com.zerobase.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberServiceImpl memberService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/main/partner")
    public String partner(Authentication authentication) {
        if (memberService.getMemberType(authentication.getName()).equals("user")) {
            return "index";
        }
        return "main/partner";
    }

    @RequestMapping("/main/user")
    public String user(Authentication authentication) {
        if (memberService.getMemberType(authentication.getName()).equals("partner")) {
            return "index";
        }
        return "main/user";
    }
}