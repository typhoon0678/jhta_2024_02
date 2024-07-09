package com.typhoon0678.security.dao;

import com.typhoon0678.security.dto.LoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    int signup(LoginDto loginDto);

    LoginDto login(String username);


}
