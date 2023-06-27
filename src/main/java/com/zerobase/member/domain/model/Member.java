package com.zerobase.member.domain.model;

import com.zerobase.member.domain.BaseEntity;
import com.zerobase.member.domain.SignUpForm;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String memberType;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public static Member buildUser(SignUpForm form) {
        String encPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());
        return Member.builder()
                .username(form.getUsername())
                .password(encPassword)
                .memberType(form.getMemberType())
                .build();
    }

}