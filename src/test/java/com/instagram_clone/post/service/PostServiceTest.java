package com.instagram_clone.post.service;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.service.MemberCommonService;
import com.instagram_clone.post.domain.Post;
import com.instagram_clone.post.repository.PostRepository;
import com.instagram_clone.post.request.AddPostForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PostServiceTest {
    @Mock
    private MemberCommonService memberCommonService;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        given(memberCommonService.getMember(any()))
                .willReturn(Member.builder().id(1L).build());
    }

    @DisplayName("게시글이 만들어져야한다.")
    @Test
    void post_o() throws Exception {
        //given 준비
        AddPostForm addPostForm = AddPostForm.builder()
                .caption("caption")
                .location("location")
                .images(null)
                .build();

        given(postRepository.save(any()))
                .willReturn(Post.builder()
                        .caption("caption")
                        .location("location")
                        .images(null)
                        .build());
        Post addPost = postService.addPost(addPostForm, 1L);
        //when 실행

        //then 검증
        assertEquals(addPost.getCaption(), "caption");
        assertEquals(addPost.getLocation(), "location");
    }

}