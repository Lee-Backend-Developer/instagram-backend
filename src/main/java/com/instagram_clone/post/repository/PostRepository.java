package com.instagram_clone.post.repository;

import com.instagram_clone.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByMemberId(Long memberId);
}
