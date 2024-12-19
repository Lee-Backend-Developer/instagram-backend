package com.instagram_clone.post.controller;

import com.instagram_clone.common.response.ResponseType;
import com.instagram_clone.member.response.MemberInfo;
import com.instagram_clone.post.domain.Post;
import com.instagram_clone.post.request.AddPostForm;
import com.instagram_clone.post.response.PostInfo;
import com.instagram_clone.post.response.PostResponse;
import com.instagram_clone.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostApiController {

    private final PostService postService;

    /**
     * todo 사진이 넣을 수 있어야한다.
     *
     * @return 응답메시지, 상태코드, 요청 데이터
     */
    @PostMapping
    public ResponseEntity<PostResponse> createPost(AddPostForm addPostForm, @RequestPart(required = false) MultipartFile image) {
        log.info("addPostForm: {}", addPostForm);
        Long memberId = 1L; // todo 임시 데이터 값

        Post post = postService.addPost(addPostForm, memberId, image);

        PostResponse response = PostResponse.builder()
                .postInfo(getPostInfo(post))
                .state("success")
                .message("게시글이 생성되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }


    /**
     * todo 게시글 조회
     *
     * @return 게시글 번호, 내용, 지도, 이미지, 회원
     */
    @GetMapping
    public ResponseEntity<PostResponse> getPost() {
        Long memberId = 1L; // todo 임시 데이터 값

        List<ResponseType> postLists = postService.getPost(memberId)
                .stream()
                .map(post -> (ResponseType) PostInfo.builder()
                        .postId(post.getId())
                        .content(post.getCaption())
                        .image(post.getImages().get(0))
                        .memberInfo(MemberInfo.builder().email(post.getMember().getEmail()).build())
                        .build())
                .toList();

        PostResponse response = PostResponse.builder()
                .postInfo(postLists)
                .state("success")
                .message("게시글 조회 완료")
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * todo 게시글 삭제
     */
    @DeleteMapping("{postId}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        PostResponse response = PostResponse.builder()
                .state("success")
                .message("게시글이 삭제되었습니다.")
                .build();

        return ResponseEntity.ok(response);
    }

    private static PostInfo getPostInfo(Post post) {
        return PostInfo.builder()
                .postId(post.getId())
                .content(post.getCaption())
                .image(post.getImages().get(0))
                .memberInfo(MemberInfo.builder().email(post.getMember().getEmail()).build())
                .build();
    }
}
