package com.typhoon0678.ch01.repository;

import com.typhoon0678.ch01.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {

    private static final Map<Integer, Member> memberMap = new HashMap<>();
    private static int seq = 0;

    public Member saveMember(Member member) {
        member.setIdx(seq++);
        memberMap.put(member.getIdx(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberMap.values());
    }

    public Member findByIdx(int idx) {
        return memberMap.get(idx);
    }
}
