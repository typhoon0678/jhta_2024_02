package com.typhoon0678.member.service;

import com.typhoon0678.member.dto.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberService {

    public static final Map<Integer, Member> memberStore = new HashMap<>();
    private static Integer sequence = 0;

    public void saveMember(Member member) {
        memberStore.put(sequence++, getNewMember(member));
    }

    private Member getNewMember(Member member) {
        return Member.builder()
                .idx(sequence)
                .userId(member.getUserId())
                .userName(member.getUserName())
                .password(member.getPassword())
                .birth(member.getBirth())
                .build();
    }
}
