package com.instagram_clone.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Builder
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

    public Member(Long id, String email, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
