package com.instagram_clone.request.member;

import lombok.Builder;

@Builder
public record SignUpForm(
        String email,
        String password,
        String firstName,
        String lastName,
        String username
) {
}
