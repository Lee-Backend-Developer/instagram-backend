package com.instagram_clone.member.request;

import lombok.Builder;

@Builder
public record LoginForm(
        String email,
        String password) {
}
