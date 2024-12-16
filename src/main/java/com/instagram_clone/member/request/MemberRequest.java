package com.instagram_clone.member.request;

import com.instagram_clone.common.request.RequestType;
import lombok.Data;

@Data
class MemberRequest implements RequestType {
    // 공통 정보 ID
    private Long id;

    // 회원 관련 정보
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    // 로그인 폼
    public MemberRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 회원가입 폼
    public MemberRequest(String email, String firstName, String lastName, String username, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
