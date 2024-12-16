package com.instagram_clone.story.domain;

import com.instagram_clone.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Story {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String contentImage;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Builder
    public Story(Long id, String contentImage, Member member) {
        this.id = id;
        this.contentImage = contentImage;
        this.member = member;
    }
}
