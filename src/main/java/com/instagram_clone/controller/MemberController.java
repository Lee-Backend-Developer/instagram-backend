package com.instagram_clone.controller;

import com.instagram_clone.domain.Member;
import com.instagram_clone.request.member.LoginForm;
import com.instagram_clone.request.member.SignUpForm;
import com.instagram_clone.response.Response;
import com.instagram_clone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @PostMapping("login")
    public ResponseEntity<Response> login(LoginForm loginForm) {
        Member login = memberService.login(loginForm);
        return ResponseEntity.ok(Response.builder()
                .state("success")
                .data(login)
                .message("로그인 성공")
                .build());

    }
    // 회원가입
    @PostMapping("signUp")
    public ResponseEntity<Response> register(SignUpForm signUpForm) {
        Member register = memberService.signUp(signUpForm);
        return ResponseEntity.ok(Response.builder()
                .state("success")
                .data(register)
                .message("회원가입이 되었습니다.")
                .build());

    }


}
