package com.zerobase.member.service;

import com.zerobase.member.domain.SignUpForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends UserDetailsService {
    default boolean signUp(SignUpForm form) {return false;}
    default Long getUserId(String username) {return null;}
}