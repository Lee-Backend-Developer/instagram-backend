/**
 * 응답 객체
 */
package com.instagram_clone.member.response;

import com.instagram_clone.common.response.Response;
import com.instagram_clone.common.response.ResponseType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberResponse extends Response<ResponseType>{

    @Builder
    public MemberResponse(String state, String message, Object body) {
        super(state, message, body);
    }
}