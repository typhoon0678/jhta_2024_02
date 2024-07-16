package com.typhoon0678.board_practice.repository;

import com.typhoon0678.board_practice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUsername(String username);
}
