package com.instagram_clone.member.service;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.repository.MemberRepository;
import com.instagram_clone.member.request.LoginForm;
import com.instagram_clone.member.request.SignUpForm;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.instagram_clone.error.ErrorMessage.*;

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
                .findByEmail(loginForm.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));
        if (!findMember.getPassword().equals(loginForm.getPassword())) {
            throw new IllegalArgumentException(MEMBER_PASSWORD_NOT_MATCH);
        }
        return findMember;
    }

    /**
     * 회원가입
     * @param signUpForm 회원가입 폼
     */
    @Transactional
    public Member signUp(SignUpForm signUpForm) {
        String inputEmail = signUpForm.getEmail();
        if (memberRepository.existsByEmail(inputEmail)) {
            throw new EntityExistsException(MEMBER_EMAIL_DUPLICATION);
        }
        return memberRepository.save(Member.builder()
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                        .firstName(signUpForm.getFirstName())
                        .lastName(signUpForm.getLastName())
                        .username(signUpForm.getUsername())
                .build());
    }

    /**
     * < 내 정보 조회 >
     *     - 게시물 Count
     *     - 팔로워 Count
     *     - 팔로우 Count
     *     - 게시물 List
     * @param username
     * @return
     */
    public Member getMyInfo(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));

        return member;
    }
}
