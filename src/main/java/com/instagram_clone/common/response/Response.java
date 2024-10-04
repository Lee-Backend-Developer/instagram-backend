package com.instagram_clone.common.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Builder
@Data
public class Response {

    private String state;
    private Object data;
    private String message;
}
