package com.typhoon0678.ch01.repository;

import com.typhoon0678.ch01.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {

    private static final Map<Integer, Member> members = new HashMap<>();
    private static int SEQUENCE = 0;

    public List<Member> getAllMember() {
        return new ArrayList<>(members.values());
    }

    public Member getMemberById(int id) {
        return members.get(id);
    }

    public void addMember(Member member) {
        members.put(SEQUENCE++, member);
    }
}
