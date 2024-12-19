package com.instagram_clone.story.response;

import com.instagram_clone.common.response.Response;
import com.instagram_clone.common.response.ResponseType;
import lombok.Builder;

public class StoryResponse extends Response<ResponseType> {

    @Builder
    public StoryResponse(String state, String message, Object body) {
        super(state, message, body);
    }
}
