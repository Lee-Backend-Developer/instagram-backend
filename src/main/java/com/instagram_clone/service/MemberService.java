package com.instagram_clone.service;

import com.instagram_clone.domain.Member;
import com.instagram_clone.repository.MemberRepository;
import com.instagram_clone.request.member.LoginForm;
import com.instagram_clone.request.member.SignInForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 로그인
     * @param loginForm 로그인 폼
     * @return 회원
     * @throws RuntimeException
     */
    public Member getMemberByEmail(LoginForm loginForm) throws RuntimeException {
        return memberRepository
                .findByEmail(loginForm.email())
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
    }

    /**
     * 회원가입
     * @param signInForm 회원가입 폼
     */
    @Transactional
    public void addMember(SignInForm signInForm) {
        memberRepository.save(Member.builder()
                .email(signInForm.email())
                .password(signInForm.password())
                        .firstName(signInForm.firstName())
                        .lastName(signInForm.lastName())
                        .username(signInForm.username())
                .build());
    }

}
