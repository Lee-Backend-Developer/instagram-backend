package com.instagram_clone.error;

public interface ErrorMessage {

    // 회원 관련
    String MEMBER_NOT_FOUND = "회원이 존재하지 않습니다.";
    String MEMBER_PASSWORD_NOT_MATCH = "비밀번호가 일치하지 않습니다.";
    String MEMBER_EMAIL_DUPLICATION = "이미 사용중인 이메일입니다.";

    // 스토리 관련
    String STORY_NOT_FOUND = "스토리가 존재하지 않습니다.";

}
