package com.typhoon0678.jspmodel2replyboard.dao;

import com.typhoon0678.jspmodel2replyboard.connect.JdbcConnectionPool;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends JdbcConnectionPool {

	public boolean addBoard(BoardDto boardDto) {

		String sql = "INSERT INTO board values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate, 0, 1)";

		String[] values = dtoToArray(boardDto);

		boolean result;

		try {
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < values.length; i++) {
				pstmt.setString(i + 1, values[i]);
			}

			result = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			close();
		}

		return result;
	}

	public List<BoardDto> getPageBoard(int start, int end) {

		String sql = "SELECT * FROM "
			+ "(SELECT rownum AS num, b01.* FROM "
			+ "   (SELECT * FROM board ORDER BY no DESC) b01 "
			+ "WHERE relevel = 1 AND restep = 1 AND available = 1) "
			+ "WHERE num BETWEEN ? AND ? ";

		List<BoardDto> boardList;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			boardList = getBoardFromDB(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return boardList;
	}

	public BoardDto getBoard(String no) {

		String sql = "SELECT * FROM board WHERE no = ? AND available = 1";

		List<BoardDto> boardList;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			boardList = getBoardFromDB(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return boardList.get(0);
	}

	public boolean updateBoardHit(String no) {

		String sql = "UPDATE board SET hit = hit + 1 WHERE no = ?";

		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			result = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			return false;
		} finally {
			close();
		}

		return result;
	}

	public boolean updateBoardAvailable(String no) {

		String sql = "UPDATE board SET available = 0 WHERE no = ?";

		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);

			result = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			return false;
		} finally {
			close();
		}

		return result;
	}

	public int getBoardCount() {

		String sql = "SELECT COUNT(*) AS count FROM board WHERE available = 1";

		int result;

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			result = rs.next() ? rs.getInt("count") : 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return result;
	}

	public int getMaxReGroup() {

		String sql = "SELECT NVL(MAX(regroup), 0) AS max FROM board WHERE available = 1";

		int result;

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			result = rs.next() ? rs.getInt("max") : 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return result;
	}

	public int getMaxReLevel(int regroup) {

		String sql = "SELECT NVL(MAX(relevel), 0) AS max FROM board WHERE regroup = ? AND available = 1";

		int result;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regroup);

			rs = pstmt.executeQuery();

			result = rs.next() ? rs.getInt("max") : 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return result;
	}

	public int getMaxReStep(int regroup, int relevel) {

		String sql = "SELECT NVL(MAX(restep), 0) AS max FROM board WHERE regroup = ? AND relevel = ? AND available = 1";

		int result;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regroup);
			pstmt.setInt(2, relevel);

			rs = pstmt.executeQuery();

			result = rs.next() ? rs.getInt("max") : 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return result;
	}

	public List<BoardDto> getBoardByReGroup(BoardDto boardDto) {

		String sql = "SELECT * FROM board WHERE regroup = ? AND no != ? AND available = 1 ORDER BY relevel, restep";

		List<BoardDto> boardList;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDto.getReGroup());
			pstmt.setInt(2, boardDto.getNo());

			rs = pstmt.executeQuery();

			boardList = getBoardFromDB(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return boardList;
	}

	public List<BoardDto> searchBoard(String search, String searchWord) {

		String sql;

		switch (search) {
			case "subject":
				sql = "SELECT * FROM board WHERE subject LIKE '%'||?||'%' AND available = 1";
				break;
			case "content":
				sql = "SELECT * FROM board WHERE content LIKE '%'||?||'%' AND available = 1";
				break;
			case "username":
				sql = "SELECT * FROM board WHERE username LIKE '%'||?||'%' AND available = 1";
				break;
			case "all":
				sql = "SELECT * FROM board WHERE subject LIKE '%'||?||'%' OR content LIKE '%'||?||'%' AND available = 1";
				break;
			default:
				sql = "SELECT * FROM board WHERE subject LIKE '%'||?||'%' AND available = 1";
		}

		List<BoardDto> boardList;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);
			if (search.equals("all")) {
				pstmt.setString(2, searchWord);
			}

			rs = pstmt.executeQuery();

			boardList = getBoardFromDB(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}

		return boardList;
	}

	private List<BoardDto> getBoardFromDB(ResultSet rs) throws SQLException {
		List<BoardDto> boardList = new ArrayList<>();

		while (rs.next()) {
			BoardDto boardDto = BoardDto.builder()
				.no(rs.getInt("no"))
				.subject(rs.getString("subject"))
				.content(rs.getString("content"))
				.userID(rs.getString("userid"))
				.userName(rs.getString("username"))
				.reGroup(rs.getInt("regroup"))
				.reLevel(rs.getInt("relevel"))
				.reStep(rs.getInt("restep"))
				.regDate(rs.getString("regdate"))
				.hit(rs.getInt("hit"))
				.available(rs.getInt("available"))
				.build();

			boardList.add(boardDto);
		}

		return boardList;
	}

	private String[] dtoToArray(BoardDto boardDto) {

		return new String[] {
			boardDto.getSubject(),
			boardDto.getContent(),
			boardDto.getUserID(),
			boardDto.getUserName(),
			String.valueOf(boardDto.getReGroup()),
			String.valueOf(boardDto.getReLevel()),
			String.valueOf(boardDto.getReStep())
		};
	}
}