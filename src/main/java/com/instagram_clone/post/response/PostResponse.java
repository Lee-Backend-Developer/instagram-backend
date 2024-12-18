/**
 * 게시글에 대한 응답 객체
 */
package com.instagram_clone.post.response;

import com.instagram_clone.common.response.Response;
import com.instagram_clone.common.response.ResponseType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse extends Response<ResponseType> {

    // 한개 일 때
    @Builder
    public PostResponse(String state, String message, Object postInfo) {
        super(state, message, postInfo);
    }
}
