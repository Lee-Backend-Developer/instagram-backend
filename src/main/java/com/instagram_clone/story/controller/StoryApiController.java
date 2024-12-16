package com.instagram_clone.story.controller;

import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.story.domain.Story;
import com.instagram_clone.story.request.FormCreate;
import com.instagram_clone.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/story")
public class StoryApiController {
    private final MemberRepository memberRepository;
    private final StoryService storyService;

    /**
     * 스토리 한개 조회
     */
    @GetMapping
    public ResponseEntity getStory() {
        Story story = storyService.getStoryList().get(0);
        return new ResponseEntity(story, HttpStatus.OK);
    }

    // 스토리 생성
    @PostMapping
    public void createStory(FormCreate formCreate) {
        Story story = Story.builder()
                .contentImage("/images/1.jpg")
                .member(memberRepository.findById(1L).get())
                .build();
        storyService.createStory(story);
    }

    // 스토리 삭제
    @DeleteMapping
    public void deleteStory(Long storyId) {
        storyService.deleteStory(1L);
    }
}
