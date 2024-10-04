package com.instagram_clone.member.domain;

import com.instagram_clone.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Member {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Builder
    public Member(Long id, String email, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
