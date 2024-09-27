package com.instagram_clone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
public class Member {
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
