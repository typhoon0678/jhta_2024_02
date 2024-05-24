package com.typhoon0678.jspmodel2.dao;

import com.typhoon0678.jspmodel2.connect.JdbcConnectionPool;
import com.typhoon0678.jspmodel2.dto.BoardDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends JdbcConnectionPool {

    public boolean addBoard(BoardDto boardDto) {

        String sql = "INSERT INTO board values(board_seq.nextval, ?, ?, ?, ?, sysdate, ?)";

        String[] values = dtoToArray(boardDto);

        boolean result = false;

        try {
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }

            result = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            close();
        }

        return result;
    }

    public List<BoardDto> getAllBoard() {

        String sql = "SELECT * FROM board";

        List<BoardDto> boardList = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            boardList = getBoardFromDB(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return boardList;
    }

    public BoardDto getBoard(String boardNo) {

        String sql = "SELECT * FROM board WHERE boardno = ?";

        List<BoardDto> boardList = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardNo);

            rs = pstmt.executeQuery();

            boardList = getBoardFromDB(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }

        return boardList.get(0);
    }

    public boolean updateBoardHit(String boardNo) {

        String sql = "UPDATE board SET hit = hit + 1 WHERE boardno = ?";

        boolean result = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardNo);

            result = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            close();
        }

        return result;
    }

    private List<BoardDto> getBoardFromDB(ResultSet rs) throws SQLException {
        List<BoardDto> boardList = new ArrayList<>();

        while (rs.next()) {
            BoardDto boardDto = BoardDto.builder()
                    .boardNo(rs.getInt("boardno"))
                    .subject(rs.getString("subject"))
                    .content(rs.getString("content"))
                    .userID(rs.getString("userid"))
                    .userName(rs.getString("username"))
                    .regDate(rs.getString("regdate"))
                    .hit(rs.getInt("hit"))
                    .build();

            boardList.add(boardDto);
        }

        return boardList;
    }

    private String[] dtoToArray(BoardDto boardDto) {

        return new String[]{
                boardDto.getSubject(),
                boardDto.getContent(),
                boardDto.getUserID(),
                boardDto.getUserName(),
                String.valueOf(boardDto.getHit())
        };
    }
}
