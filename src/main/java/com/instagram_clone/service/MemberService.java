package com.instagram_clone.service;

import com.instagram_clone.domain.Member;
import com.instagram_clone.repository.MemberRepository;
import com.instagram_clone.request.member.LoginForm;
import com.instagram_clone.request.member.SignUpForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.instagram_clone.message.ErrorMessage.*;

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
    public Member login(LoginForm loginForm) throws RuntimeException {
        Member findMember = memberRepository
                .findByEmail(loginForm.email())
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));
        if (!findMember.getPassword().equals(loginForm.password())) {
            throw new IllegalArgumentException(PASSWORD_NOT_MATCH);
        }
        return findMember;
    }

    /**
     * 회원가입
     * @param signUpForm 회원가입 폼
     */
    @Transactional
    public Member signUp(SignUpForm signUpForm) {
        return memberRepository.save(Member.builder()
                .email(signUpForm.email())
                .password(signUpForm.password())
                        .firstName(signUpForm.firstName())
                        .lastName(signUpForm.lastName())
                        .username(signUpForm.username())
                .build());
    }

}
