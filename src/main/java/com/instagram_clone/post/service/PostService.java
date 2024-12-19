package com.instagram_clone.post.service;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.service.MemberCommonService;
import com.instagram_clone.post.domain.Post;
import com.instagram_clone.post.repository.PostRepository;
import com.instagram_clone.post.request.AddPostForm;
import com.instagram_clone.util.FileSaveUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final MemberCommonService memberCommonService;
    private final PostRepository postRepository;

    // 게시글 추가
    @Transactional
    public Post addPost(AddPostForm post, Long memberId, MultipartFile image) {
        Member member = memberCommonService.getMember(memberId);

        String fileName = FileSaveUtil.uploadImage(image);

        Post addPost = Post.builder()
                .member(member)
                .caption(post.getCaption())
                .location(post.getLocation())
                .images(List.of(fileName))
                .build();
        return postRepository.save(addPost);
    }

    // 게시글 조회
    public List<Post> getPost(Long memberId) {
        return postRepository.findByMemberId(memberId);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }


}
