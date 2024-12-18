package com.instagram_clone.member.controller;

import com.instagram_clone.common.response.ResponseType;
import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.request.LoginForm;
import com.instagram_clone.member.request.SignUpForm;
import com.instagram_clone.common.response.Response;
import com.instagram_clone.member.response.MemberInfo;
import com.instagram_clone.member.response.MemberResponse;
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
    public ResponseEntity<MemberResponse> login(@RequestBody LoginForm loginForm) {
        Member login = memberService.login(loginForm);
        // 무조건 response 객체를 만들어야됨!!
        MemberResponse response = MemberResponse.builder()
                .state("success")
                .message("로그인이 되었습니다.")
                .body(getMemberResponse(login))
                .build();

        return ResponseEntity.ok(response);

    }

    // 회원가입
    @PostMapping("signUp")
    public ResponseEntity<MemberResponse> register(@RequestBody SignUpForm signUpForm) {
        Member register = memberService.signUp(signUpForm);

        MemberResponse response = MemberResponse.builder()
                .state("success")
                .message("회원가입이 완료 되었습니다.")
                .body(getMemberResponse(register))
                .build();

        return ResponseEntity.ok(response);

    }

    // 내 정보
    @GetMapping("{username}")
    public ResponseEntity<MemberResponse> getMyInfo(@PathVariable String username) {
        Member myInfo = memberService.getMyInfo(username);
        MemberResponse response = MemberResponse.builder()
                .state("success")
                .message("조회완료")
                .body(getMemberResponse(myInfo))
                .build();
        return ResponseEntity.ok(response);
    }

    private static MemberInfo getMemberResponse(Member myInfo) {
        return MemberInfo.builder()
                .username(myInfo.getUsername())
                .email(myInfo.getEmail())
                .firstName(myInfo.getFirstName())
                .lastName(myInfo.getLastName())
                .password("******")
                .build();
    }
}
