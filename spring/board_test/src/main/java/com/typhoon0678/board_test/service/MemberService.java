package com.typhoon0678.board_test.service;

import com.typhoon0678.board_test.domain.Member;
import com.typhoon0678.board_test.dto.MemberDto;
import com.typhoon0678.board_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public boolean findMember(MemberDto memberDto) {
        return memberRepository.existsByMemberDto(
                memberDto.getUsername(),
                memberDto.getPassword());
    }
}
