/**
 * Response.java
 * 응답 객체는 이 클래스를 통해서 만들어져야함
 */
package com.instagram_clone.common.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Response <T extends ResponseType> {
    // http 에러코드
    private String state;
    // 응답 메시지
    private List<T> bodies = new ArrayList<>();
    // 에러 메시지
    private String message;


    private Response(String state, String message) {
        this.state = state;
        this.message = message;
    }

    // 하나 일 때 두개 일 때 나눠야하는 초기화
    public Response(String state, String message, Object o) {
        this(state, message);

        if (o instanceof ResponseType) { // 하나 일 때
            this.bodies.add((T) o);
        } else { // 2개 이상일 때
            this.bodies = (List<T>) o;

        }
    }

}
