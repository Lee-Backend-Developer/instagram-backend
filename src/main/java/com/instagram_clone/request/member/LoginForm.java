package com.instagram_clone.request.member;

import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder
public record LoginForm(
        String email,
        String password) {
}
