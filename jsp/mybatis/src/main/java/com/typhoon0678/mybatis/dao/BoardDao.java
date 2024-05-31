package com.typhoon0678.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.typhoon0678.mybatis.dto.BoardDto;
import com.typhoon0678.mybatis.dto.PageDto;
import com.typhoon0678.mybatis.dto.SearchDto;
import com.typhoon0678.mybatis.mybatis.MybatisConnectionFactory;

public class BoardDao {

	SqlSession sqlSession;
	boolean isCommit = true;

	public BoardDao() {
		sqlSession = MybatisConnectionFactory.getSqlSession(true);
	}

	public BoardDao(boolean isCommit) {
		this.isCommit = isCommit;
		sqlSession = MybatisConnectionFactory.getSqlSession(isCommit);
	}


	public BoardDto getBoard(int no) {
		return sqlSession.selectOne("getBoard", no);
	}

	public List<BoardDto> getBoardList(PageDto pageDto) {

		return sqlSession.selectList("getBoardList", pageDto);
	}

	public int getTotal() {
		return sqlSession.selectOne("getTotal");
	}

	public List<BoardDto> getSearchBoardList(SearchDto searchDto) {

		return sqlSession.selectList("getSearchBoardList", searchDto);
	}

	public int getSearchTotal(SearchDto searchDto) {
		return sqlSession.selectOne("getSearchTotal", searchDto);
	}

	public int getMaxReGroup() {
		return sqlSession.selectOne("getMaxReGroup");
	}

	public int writeBoard(BoardDto boardDto) {
		return sqlSession.insert("writeBoard", boardDto);
	}

	public int deleteAllBoard(int[] noArray) {
		int result = sqlSession.delete("deleteAllBoard", noArray);

		if (!this.isCommit && result != noArray.length) {
			sqlSession.rollback();
		} else {
			sqlSession.commit();
		}

		return result;
	}


	public void close() {
		sqlSession.close();
	}
}
