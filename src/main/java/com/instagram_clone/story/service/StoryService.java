package com.instagram_clone.story.service;

import com.instagram_clone.error.ErrorMessage;
import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.story.domain.Story;
import com.instagram_clone.story.repository.StoryRepository;
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
public class StoryService {
    private final StoryRepository storyRepository;
    private final MemberRepository memberRepository;

    public Story getStory(Long storyId) {
        return storyRepository.findById(storyId).orElseThrow(() -> new EntityNotFoundException(ErrorMessage.STORY_NOT_FOUND));
    }

    // 스토리 여러개 조회
    public List<Story> getStoryList() {
        return storyRepository.findAll();
    }

    // 스토리 생성
    @Transactional
    public Story createStory(Story story, MultipartFile storyFile){
        String fileName = FileSaveUtil.uploadImage(storyFile);

        Story saveStory = Story.builder()
                .contentImage(fileName)
                .member(getMember(story))
                .build();

        return storyRepository.save(saveStory);
    }

    public void deleteStory(long storyId) {
        storyRepository.deleteById(storyId);
    }
    // 스토리 삭제


    private Member getMember(Story story) {
        return memberRepository.findById(story.getMember().getId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

}
