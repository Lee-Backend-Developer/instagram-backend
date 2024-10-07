package com.instagram_clone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram_clone.VariableCommon;
import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.controller.MemberController;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.member.request.LoginForm;
import com.instagram_clone.member.request.SignUpForm;
import com.instagram_clone.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @DisplayName("로그인이 되어야한다.")
    @Test
    void login_O() throws Exception {
        //given
        Member saveMember = Member.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD).build();
        given(memberService.login(any()))
                .willReturn(saveMember);

        //then
        LoginForm loginForm = LoginForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .build();
        String loginFormJson = objectMapper.writeValueAsString(loginForm);
        mockMvc.perform(post("/api/member/login")
                        .contentType("application/json")
                        .content(loginFormJson)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("회원가입이 되어야한다.")
    @Test
    void signup_O() throws Exception {
        //when 실행
        when(memberService.signUp(any()))
                .thenReturn(Member.builder()
                        .email(VariableCommon.EMAIL)
                        .password(VariableCommon.PASSWORD)
                        .firstName(VariableCommon.FIRST_NAME)
                        .lastName(VariableCommon.LAST_NAME)
                        .username(VariableCommon.USERNAME)
                        .build());


        //then 검증
        SignUpForm signUpForm = SignUpForm.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD)
                .firstName(VariableCommon.FIRST_NAME)
                .lastName(VariableCommon.LAST_NAME)
                .username(VariableCommon.USERNAME)
                .build();
        String signUpFormJson = objectMapper.writeValueAsString(signUpForm);

        mockMvc.perform(post("/api/member/signUp")
                        .contentType("application/json")
                        .content(signUpFormJson)
                )
                .andDo(print())
                .andExpect(jsonPath("$.message").value("회원가입이 되었습니다."));

    }

    /**
     * 내 정보가 조회되어야한다.
     * 게시물 count
     * 팔로워 count
     * 팔로우 count
     * 게시물 list
     * @throws Exception
     */
    @DisplayName("내 정보가 조회되어야한다.")
    @Test
    void info_o() throws Exception {
        //given
        Member saveMember = Member.builder()
                .email(VariableCommon.EMAIL)
                .password(VariableCommon.PASSWORD).build();
        given(memberService.login(any()))
                .willReturn(saveMember);

        //when 실행

        //then 검증
        mockMvc.perform(post("/api/member/admin/"))
                .andDo(print())
                .andExpect(jsonPath("$.postCount")
                        .value(0));

    }
}