package com.typhoon0678.board_test.repository;

import com.typhoon0678.board_test.domain.Member;
import com.typhoon0678.board_test.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select username, password from Member where username = :username")
    MemberDto findByUsername(@Param(value = "username") String username);

    @Query("select count(m.id) > 0 " +
            "from Member m " +
            "where m.username = :username and m.password = :password")
    boolean existsByMemberDto(
            @Param(value = "username") String username,
            @Param(value = "password") String password);
}
