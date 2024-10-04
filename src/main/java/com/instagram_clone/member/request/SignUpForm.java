package com.instagram_clone.member.request;

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
