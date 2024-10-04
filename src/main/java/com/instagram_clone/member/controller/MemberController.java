package com.instagram_clone.member.controller;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.request.LoginForm;
import com.instagram_clone.member.request.SignUpForm;
import com.instagram_clone.common.response.Response;
import com.instagram_clone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // 로그인
    @PostMapping("login")
    public ResponseEntity<Response> login(@RequestBody LoginForm loginForm) {
        Member login = memberService.login(loginForm);
        return ResponseEntity.ok(Response.builder()
                .state("success")
                .data(login)
                .message("로그인 성공")
                .build());

    }

    // 회원가입
    @PostMapping("signUp")
    public ResponseEntity<Response> register(@RequestBody SignUpForm signUpForm) {
        Member register = memberService.signUp(signUpForm);
        return ResponseEntity.ok(Response.builder()
                .state("success")
                .data(register)
                .message("회원가입이 되었습니다.")
                .build());

    }

    // 내 정보
    @GetMapping("{username}/")
    public ResponseEntity<Response> getMyInfo(@PathVariable String username) {
        Member myInfo = memberService.getMyInfo(username);
        return ResponseEntity.ok(Response.builder()
                .state("success")
                .data(myInfo)
                .message("내 정보 조회 성공")
                .build());
    }
}
