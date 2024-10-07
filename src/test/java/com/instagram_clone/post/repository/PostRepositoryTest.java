package com.instagram_clone.post.repository;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.post.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PostRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(Member.builder().id(1L).build());
    }

    @Test
    void findByMemberId() {
        // given
        Member member = memberRepository.findById(1L).orElseThrow();

        postRepository.save(Post.builder()
                .caption("caption")
                .location("location")
                .images(null)
                        .member(member)
                .build());

        postRepository.save(Post.builder()
                .caption("caption01")
                .location("location01")
                .images(null)
                        .member(member)
                .build());


        // when
        List<Post> postLists = postRepository.findByMemberId(1L);

        // then
        assertEquals(postLists.get(0).getCaption(), "caption");
        assertEquals(postLists.get(1).getCaption(), "caption01");
        assertEquals(postLists.size(), 2);

    }
}