package com.instagram_clone.service;

import com.instagram_clone.VariableCommon;
import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.member.request.LoginForm;
import com.instagram_clone.member.request.SignUpForm;
import com.instagram_clone.member.service.MemberService;
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
import static org.mockito.Mockito.when;

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
                        .email(VariableCommon.EMAIL)
                        .password(VariableCommon.PASSWORD).build()));
        //when 실행
        LoginForm loginForm = LoginForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .build();
        Member member = memberService.login(loginForm);

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
        assertThrows(EntityNotFoundException.class, () -> memberService.login(loginForm));
    }

    @DisplayName("회원가입이 되어야한다.")
    @Test
    void signUp_O() throws Exception {
        //when 실행
        when(memberRepository.save(any()))
                .thenReturn(Member.builder()
                        .email(VariableCommon.EMAIL)
                        .password(VariableCommon.PASSWORD)
                        .firstName(VariableCommon.FIRST_NAME)
                        .lastName(VariableCommon.LAST_NAME)
                        .username(VariableCommon.USERNAME)
                        .build());

        SignUpForm signUpForm = SignUpForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .firstName(VariableCommon.FIRST_NAME)
                .lastName(VariableCommon.LAST_NAME)
                .username(VariableCommon.USERNAME)
                .build();

        Member signup = memberService.signUp(signUpForm);

        //then 검증
        assertEquals(signup.getEmail(), VariableCommon.EMAIL);
        assertEquals(signup.getPassword(), VariableCommon.PASSWORD);
    }
}