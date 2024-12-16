package com.instagram_clone.member.request;

import lombok.Builder;

public class SignUpForm extends MemberRequest {

    @Builder
    public SignUpForm(String email, String firstName, String lastName, String username, String password) {
        super(email, firstName, lastName, username, password);
    }
}
