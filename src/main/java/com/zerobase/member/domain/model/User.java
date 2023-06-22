package com.zerobase.member.domain.model;

import com.zerobase.member.domain.SignUpForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    public static User buildUser(SignUpForm form) {
        return User.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
    }

}