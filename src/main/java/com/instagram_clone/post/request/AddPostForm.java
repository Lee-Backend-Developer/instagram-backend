package com.instagram_clone.post.request;

import lombok.Builder;

import java.util.List;


public class AddPostForm extends PostForm {

    @Builder
    public AddPostForm(String caption, String location, List<String> images) {
        super(caption, location, images);
    }
}
