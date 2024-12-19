package com.instagram_clone.story.response;

import com.instagram_clone.common.response.ResponseType;
import lombok.Builder;

@Builder
public record StoryInfo(
        Long id,
        String contentImageLocation,
        String memberName
        ) implements ResponseType { }
