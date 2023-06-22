package com.zerobase.member.service;

import com.zerobase.member.domain.SignUpForm;
import com.zerobase.member.domain.model.Partner;
import com.zerobase.member.domain.model.User;
import com.zerobase.member.repository.PartnerRepository;
import com.zerobase.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public boolean signUpUser(SignUpForm form) {
        Optional<User> optionalUser = userRepository.findByUsername(form.getUsername());
        if (optionalUser.isPresent()) {
            return false;
        }
        User user = User.buildUser(form);
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean signUpPartner(SignUpForm form) {
        Partner partner = Partner.buildPartner(form);
        partnerRepository.save(partner);
        return true;
    }


}