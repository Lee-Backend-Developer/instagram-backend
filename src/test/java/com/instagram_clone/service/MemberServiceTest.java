package com.instagram_clone.service;

import com.instagram_clone.VariableCommon;
import com.instagram_clone.domain.Member;
import com.instagram_clone.repository.MemberRepository;
import com.instagram_clone.request.member.LoginForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("로그인이 되어야한다.")
    @Test
    void login_o() throws Exception {
        //given 준비 과정
        given(memberRepository.findByEmail(anyString()))
                .willReturn(Optional.ofNullable(Member.builder()
                        .email("admin@instagram.com")
                        .password("admin").build()));
        //when 실행
        LoginForm loginForm = LoginForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .build();
        Member member = memberService.getMemberByEmail(loginForm);

        //then 검증
        assertEquals(member.getEmail(), loginForm.email());
    }

    @DisplayName("회원이 없다면 로그인이 되지 않아야한다.")
    @Test
    void login_throw() throws Exception {
        //given 준비 과정
        given(memberRepository.findByEmail(anyString()))
                .willThrow(new EntityNotFoundException("회원이 존재하지 않습니다."));
        //when 실행
        LoginForm loginForm = LoginForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .build();

        //then 검증
        assertThrows(EntityNotFoundException.class, () -> memberService.getMemberByEmail(loginForm));
    }

    @DisplayName("회원가입이 되어야한다.")
    @Test
    void signin_O() throws Exception {
        //given 준비 과정
        LoginForm loginForm = LoginForm.builder()
                .email("admin@naver.com")
                .password("1234")
                .build();

        //when 실행

        //then 검증

    }

}