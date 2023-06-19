package com.zerobase.reservation.service;

import com.zerobase.reservation.domain.SignUpForm;
import com.zerobase.reservation.domain.model.User;
import com.zerobase.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpUserService {

    private final UserRepository userRepository;

    public User signUp(SignUpForm form) {
        return User.from(form);
    }
}