package com.instagram_clone.post.request;

import lombok.Builder;

import java.util.List;

@Builder
public record AddPostForm(
        String caption,
        String location,
        List<String> images
) {
}
