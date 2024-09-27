package com.instagram_clone.service;

import com.instagram_clone.domain.Member;
import com.instagram_clone.repository.MemberRepository;
import com.instagram_clone.request.member.LoginForm;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member getMemberByEmail(LoginForm loginForm) throws RuntimeException {
        return memberRepository
                .findByEmail(loginForm.email())
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
    }

}
