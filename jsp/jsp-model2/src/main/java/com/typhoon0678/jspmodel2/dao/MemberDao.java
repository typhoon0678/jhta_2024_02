package com.typhoon0678.jspmodel2.dao;

import com.typhoon0678.jspmodel2.connect.JdbcConnectionPool;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import com.typhoon0678.jspmodel2.dto.SessionMemberDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends JdbcConnectionPool {

    public int insertMember(MemberDto memberDto) {
        int result = 0;

        String sql = "INSERT INTO MEMBER VALUES(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        } finally {
            close();
        }

        return result;
    }


    public boolean deleteMember(MemberDto memberDto) {
        boolean result = false;

        String sql = "DELETE FROM MEMBER WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getUserID());

            result = pstmt.executeUpdate() > 0;

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
                .originalProfile(memberDto.getOriginalProfile())
                .renameProfile(memberDto.getRenameProfile())
                .build();

    }

    public boolean isIdDuplicated(String userID) {
        boolean result;

        String sql = "SELECT * FROM MEMBER WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);

            result = pstmt.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return result;
    }

    public boolean checkMember(MemberDto memberDto) {
        String hashPW = "";

        String sql = "SELECT * FROM MEMBER WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getUserID());

            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                hashPW = results.getString("userPW");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return !hashPW.isEmpty() && BCrypt.checkpw(memberDto.getUserPW(), hashPW);
    }

    public boolean resetPassword(MemberDto memberDto) {
        boolean result = false;

        String sql = "UPDATE MEMBER SET userPW = ? WHERE userID = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDto.getUserPW());
            pstmt.setString(2, memberDto.getUserID());

            result = pstmt.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return result;
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
                .grade(rs.getString("grade"))
                .birth(rs.getString("birth"))
                .originalProfile(rs.getString("original_profile"))
                .renameProfile(rs.getString("rename_profile"))
                .build();
    }
}
