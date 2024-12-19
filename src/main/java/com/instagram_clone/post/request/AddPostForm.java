package com.instagram_clone.post.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class AddPostForm extends PostForm {

    @Builder
    public AddPostForm(String caption, String location) {
        super(caption, location);
    }
}
