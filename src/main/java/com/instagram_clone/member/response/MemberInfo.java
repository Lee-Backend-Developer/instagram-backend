package com.instagram_clone.member.response;

import com.instagram_clone.common.response.ResponseType;
import lombok.Builder;
import lombok.Getter;

@Builder
public record MemberInfo (
        Long id,
        String email,
        String firstName,
        String lastName,
        String username,
        String password
) implements ResponseType { }
