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

    public void saveMember(Member member) {
        memberRepository.addMember(member);
    }

    public List<Member> getMemberList() {
        return memberRepository.getAllMember();
    }

    public Member getMemberById(int id) {
        return memberRepository.getMemberById(id);
    }

}
