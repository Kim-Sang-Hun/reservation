package com.zerobase.member.service;

import com.zerobase.member.domain.SignUpForm;
import com.zerobase.member.domain.model.Member;
import com.zerobase.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean signUp(SignUpForm form) {
        Optional<Member> optionalUser = memberRepository.findByUsername(form.getUsername());
        if (optionalUser.isPresent()) {
            return false;
        }
        Member member = Member.buildUser(form);
        memberRepository.save(member);
        return true;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PARTNER"));


        return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
    }
}