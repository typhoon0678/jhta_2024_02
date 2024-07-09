package com.typhoon0678.board_test2.dao;

import com.typhoon0678.board_test2.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    int signup(MemberDto memberDto);

    MemberDto login(String username);

    int duplicate(String username);
}
