package com.zerobase.member.domain.model;

import com.zerobase.member.domain.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Partner {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public static Partner buildPartner(SignUpForm form) {
        return Partner.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
    }
}