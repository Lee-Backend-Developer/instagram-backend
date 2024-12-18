package com.instagram_clone.post.response;

import com.instagram_clone.common.response.ResponseType;
import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.response.MemberInfo;
import lombok.Builder;
import lombok.Data;

@Builder
public record PostInfo (
        Long postId,
        String content,
        String map,
        String image,
        MemberInfo memberInfo
) implements ResponseType{}