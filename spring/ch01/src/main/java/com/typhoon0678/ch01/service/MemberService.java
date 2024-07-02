package com.typhoon0678.ch01.service;

import com.typhoon0678.ch01.dto.Member;
import com.typhoon0678.ch01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member) {
        return memberRepository.saveMember(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberByIdx(int idx) {
        return memberRepository.findByIdx(idx);
    }
}
