package com.instagram_clone.post.request;

import com.instagram_clone.common.request.RequestType;
import lombok.Data;

import java.util.List;

@Data
public class PostForm implements RequestType {
    // 공통 정보 ID
    private Long id;

    // 포스트 관련 정보
    private String caption;
    private String location;
    private List<String> image;

    // 포스트 추가 폼
    public PostForm(String caption, String location, List<String> image) {
        this.caption = caption;
        this.location = location;
        this.image = image;
    }
}
