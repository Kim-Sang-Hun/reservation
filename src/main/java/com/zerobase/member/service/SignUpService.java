package com.zerobase.member.service;

import com.zerobase.member.domain.SignUpForm;
import org.springframework.stereotype.Service;

@Service
public interface SignUpService {

    default boolean signUpUser(SignUpForm form) {return false;}
    default boolean signUpPartner(SignUpForm form) {return false;}

}