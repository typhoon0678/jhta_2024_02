package com.typhoon0678.ch01.service;

import com.typhoon0678.ch01.dto.Member;
import com.typhoon0678.ch01.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("member repository에서 저장한 member랑 return되는 member가 같다")
    void saveMember() {

        memberRepository.saveMember(
                Member.builder()
                        .userName("username")
                        .userPw("1234")
                        .userId("userId")
                        .build());
    }
}