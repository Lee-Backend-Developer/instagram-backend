package com.instagram_clone.post.request;

import com.instagram_clone.common.request.RequestType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostForm implements RequestType {
    // 공통 정보 ID
    private Long id;

    // 포스트 관련 정보
    private String caption;
    private String location;

    // 포스트 추가 폼
    public PostForm(String caption, String location) {
        this.caption = caption;
        this.location = location;
    }
}
