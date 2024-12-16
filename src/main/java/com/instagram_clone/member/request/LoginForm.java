package com.instagram_clone.member.request;

import lombok.Builder;

public class LoginForm extends MemberRequest {

    @Builder
    public LoginForm(String email, String password) {
        super(email, password);
    }
}
