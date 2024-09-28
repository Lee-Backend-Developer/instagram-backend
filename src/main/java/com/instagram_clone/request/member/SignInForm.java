package com.instagram_clone.request.member;

import lombok.Builder;

@Builder
public record SignInForm(
        String email,
        String password,
        String firstName,
        String lastName,
        String username
) {
}
