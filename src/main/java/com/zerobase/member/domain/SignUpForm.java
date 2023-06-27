package com.zerobase.member.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignUpForm {
    private String username;
    private String password;
    private String memberType;
}