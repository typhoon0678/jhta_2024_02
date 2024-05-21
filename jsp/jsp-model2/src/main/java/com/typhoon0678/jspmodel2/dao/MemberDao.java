package com.typhoon0678.jspmodel2.dao;

import com.typhoon0678.jspmodel2.connect.JdbcConnectionPool;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import com.typhoon0678.jspmodel2.dto.SessionMemberDto;

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

    public boolean deleteMemberAdmin(String userID) {
        boolean result = false;

        String sql = "DELETE FROM MEMBER WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);

            result = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public boolean deleteMember(MemberDto memberDto) {
        boolean result = false;

        String sql = "DELETE FROM MEMBER WHERE userID = ? AND userPW = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getUserID());
            pstmt.setString(2, memberDto.getUserPW());

            result = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<MemberDto> getMembers() {
        List<MemberDto> members = new ArrayList<>();

        String sql = "SELECT * FROM MEMBER";

        try {
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDto memberDto = new MemberDto();

                memberDto = doMemberDto(rs);

                members.add(memberDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return members;
    }

    public SessionMemberDto getMember(String userId) {
        MemberDto memberDto = new MemberDto();

        String sql = "SELECT * FROM MEMBER WHERE userId = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                memberDto = doMemberDto(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return SessionMemberDto.builder()
                .userNo(memberDto.getUserNo())
                .userID(memberDto.getUserID())
                .userName(memberDto.getUserName())
                .email(memberDto.getEmail())
                .postcode(memberDto.getPostcode())
                .address(memberDto.getAddress())
                .detailAddress(memberDto.getDetailAddress())
                .birth(memberDto.getBirth())
                .build();

    }

    public MemberDto getMember(int userNo) {
        MemberDto memberDto = new MemberDto();

        String sql = "SELECT * FROM MEMBER WHERE userNo = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userNo);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                memberDto = doMemberDto(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return memberDto;
    }

    private MemberDto doMemberDto(ResultSet rs) throws SQLException {
        return MemberDto.builder()
                .userNo(rs.getInt("userNo"))
                .userID(rs.getString("userID"))
                .userPW(rs.getString("userPW"))
                .userName(rs.getString("userName"))
                .email(rs.getString("email"))
                .postcode(rs.getString("postcode"))
                .address(rs.getString("address"))
                .detailAddress(rs.getString("address_detail"))
                .birth(rs.getString("birth"))
                .build();
    }

    public boolean isIdDuplitated(String userID) {
        boolean result;

        String sql = "SELECT * FROM MEMBER WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);

            result = pstmt.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public boolean checkMember(MemberDto memberDto) {
        boolean result;

        String sql = "SELECT * FROM MEMBER WHERE userID = ? AND userPW = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getUserID());
            pstmt.setString(2, memberDto.getUserPW());

            result = pstmt.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return result;
    }
}
