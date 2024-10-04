package com.instagram_clone.post.domain;

import com.instagram_clone.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String caption;
    private String location;

    // 이미지 파일 이름, 확장자만 저장 ex) image.jpg
    // @Convert(converter = StringListConverter.class)도 고민 해볼수 있겠음 애초에 Convert는 컬럼이 정확히 일치 하지 않을때 사용
    @ElementCollection
    private List<String> images;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
