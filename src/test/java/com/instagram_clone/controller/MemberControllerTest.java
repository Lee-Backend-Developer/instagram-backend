package com.instagram_clone.controller;

import com.instagram_clone.VariableCommon;
import com.instagram_clone.domain.Member;
import com.instagram_clone.repository.MemberRepository;
import com.instagram_clone.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
        mockMvc.perform(post("/api/member/login")
                        .formField("email", VariableCommon.EMAIL)
                        .formField("password", VariableCommon.PASSWORD))
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
        mockMvc.perform(post("/api/member/signUp")
                        .formField("email", VariableCommon.EMAIL)
                        .formField("password", VariableCommon.PASSWORD)
                        .formField("firstName", VariableCommon.FIRST_NAME)
                        .formField("lastName", VariableCommon.LAST_NAME)
                        .formField("username", VariableCommon.USERNAME)
                )
                .andDo(print())
                .andExpect(jsonPath("$.message").value("회원가입이 되었습니다."));

    }
}