package com.instagram_clone.story.service;

import com.instagram_clone.story.domain.Story;
import com.instagram_clone.story.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoryService {
    private final StoryRepository storyRepository;

    // 스토리 조회
    public List<Story> getStoryList() {
        return storyRepository.findAll();
    }

    // 스토리 생성
    @Transactional
    public Story createStory(Story story){
        return storyRepository.save(story);
    }

    public void deleteStory(long storyId) {
        storyRepository.deleteById(storyId);
    }
    // 스토리 삭제

}
