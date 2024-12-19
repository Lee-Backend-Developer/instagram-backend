package com.instagram_clone.story.controller;

import com.instagram_clone.common.response.Response;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.story.domain.Story;
import com.instagram_clone.story.request.FormCreate;
import com.instagram_clone.story.response.StoryInfo;
import com.instagram_clone.story.response.StoryResponse;
import com.instagram_clone.story.service.StoryService;
import com.instagram_clone.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @GetMapping("/{storyId}")
    public ResponseEntity<StoryResponse> getStory(@PathVariable Long storyId) {
        Story story = storyService.getStory(storyId);

        StoryResponse response = StoryResponse.builder()
                .body(getStoryInfo(story))
                .state("스토리가 조회 되었습니다")
                .message("success")
                .build();

        return ResponseEntity.ok(response);
    }

    // 스토리 여러개 조회
    public ResponseEntity<StoryResponse> getStories(){
        List<Story> storyList = storyService.getStoryList();

        StoryResponse response = StoryResponse.builder()
                .body(storyList)
                .state("스토리가 조회 되었습니다")
                .message("success")
                .build();

        return ResponseEntity.ok(response);

    }



    // 스토리 생성
    @PostMapping
    public ResponseEntity<StoryResponse> createStory(FormCreate form, @RequestPart(required = false) MultipartFile image) {
        Story story = Story.builder()
                .member(memberRepository.findById(1L).get())
                .build();
        storyService.createStory(story, image);
        return ResponseEntity.ok().build();
    }

    // 스토리 삭제
    @DeleteMapping
    public ResponseEntity<StoryResponse> deleteStory(Long storyId) {
        storyService.deleteStory(storyId);
        return ResponseEntity.ok().build();
    }

    private static StoryInfo getStoryInfo(Story story) {
        return StoryInfo.builder()
                .contentImageLocation(story.getContentImage())
                .memberName(story.getMember().getUsername())
                .build();
    }
}
