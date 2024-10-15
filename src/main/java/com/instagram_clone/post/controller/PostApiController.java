package com.instagram_clone.post.controller;

import com.instagram_clone.common.response.Response;
import com.instagram_clone.post.request.AddPostForm;
import com.instagram_clone.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostApiController {

    private final PostService postService;

    /**
     * todo 게시글 생성
     *
     * @return 응답메시지, 상태코드, 요청 데이터
     */
    @PostMapping
    public ResponseEntity<Response> createPost(@RequestBody AddPostForm addPostForm) {
        Long memberId = 1L; // todo 임시 데이터 값

        postService.addPost(addPostForm, memberId);

        Response response = Response.builder()
                .message("success")
                .state(HttpStatus.OK.getReasonPhrase())
                .data(addPostForm)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * todo 게시글 조회
     *
     * @return 게시글 번호, 내용, 지도, 이미지, 회원
     */
    @GetMapping
    public ResponseEntity<Response> getPost() {
        Long memberId = 1L; // todo 임시 데이터 값

        Response response = Response.builder()
                .message("success")
                .state(HttpStatus.OK.getReasonPhrase())
                .data(postService.getPost(memberId))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * todo 게시글 삭제
     */
    @DeleteMapping("{postId}")
    public ResponseEntity<Response> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        Response response = Response.builder().message("success").state(HttpStatus.OK.getReasonPhrase()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
