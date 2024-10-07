package com.instagram_clone.member.service;

import com.instagram_clone.member.domain.Member;
import com.instagram_clone.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
// 회원에 관련해서 공통으로 사용되는 서비스
public class MemberCommonService {
    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) throws EntityNotFoundException {
        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("해당 사용자가 없습니다."));
    }
}
