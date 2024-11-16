package com.instagram_clone.member.response;

import lombok.Builder;

@Builder
public record MemberInfo(
        Long id,
        String email,
        String firstName,
        String lastName,
        String username,
        String password
) {
}
