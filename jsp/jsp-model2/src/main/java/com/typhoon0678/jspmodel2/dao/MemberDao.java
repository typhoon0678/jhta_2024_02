package com.typhoon0678.jspmodel2.dao;

import com.typhoon0678.jspmodel2.connect.JdbcConnectionPool;
import com.typhoon0678.jspmodel2.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends JdbcConnectionPool {

    public int insertMember(MemberDto memberDto) {
        int result = 0;

        String sql = "INSERT INTO MEMBER VALUES(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)";

        String[] values = memberDto.getMember();

        try {
            conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close();
        }

        return result;
    }

    public List<MemberDto> getMembers() {
        List<MemberDto> members = new ArrayList<>();

        String sql = "SELECT * FROM MEMBER";

        try {
            conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDto memberDto = new MemberDto();

                memberDto.setUserNo(rs.getInt("userNo"));
                memberDto.setUserID(rs.getString("userID"));
                memberDto.setUserPW(rs.getString("userPW"));
                memberDto.setUserName(rs.getString("userName"));
                memberDto.setEmail(rs.getString("email"));
                memberDto.setPostcode(rs.getString("postcode"));
                memberDto.setAddress(rs.getString("address"));
                memberDto.setDetailAddress(rs.getString("address_detail"));
                memberDto.setBirth(rs.getString("birth"));

                members.add(memberDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return members;
    }
}
