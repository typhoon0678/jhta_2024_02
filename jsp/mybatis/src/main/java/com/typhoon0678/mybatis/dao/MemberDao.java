package com.typhoon0678.mybatis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import com.typhoon0678.mybatis.dto.MemberDto;
import com.typhoon0678.mybatis.dto.ProfileDto;
import com.typhoon0678.mybatis.dto.SessionMemberDto;
import com.typhoon0678.mybatis.mybatis.MybatisConnectionFactory;

public class MemberDao {

	SqlSession sqlSession;
	boolean isCommit = true;

	public MemberDao() {
		sqlSession = MybatisConnectionFactory.getSqlSession(true);
	}

	public MemberDao(boolean isCommit) {
		this.isCommit = isCommit;
		sqlSession = MybatisConnectionFactory.getSqlSession(isCommit);
	}

	public int insertMember(MemberDto memberDto) {
		return sqlSession.insert("insertMember", memberDto);
	}

	public int deleteMemberAdmin(String userID) {
		return sqlSession.delete("deleteMember", Integer.parseInt(userID));
	}

	public int deleteMember(MemberDto memberDto) {
		return sqlSession.delete("deleteMember", memberDto.getUserID());
	}

	public List<MemberDto> getMembers() {
		return sqlSession.selectList("getMembers");
	}

	public SessionMemberDto getMember(String userID) {
		MemberDto memberDto = sqlSession.selectOne("getMember", userID);

		return SessionMemberDto.builder()
			.userNo(memberDto.getUserNo())
			.userID(memberDto.getUserID())
			.userName(memberDto.getUserName())
			.email(memberDto.getEmail())
			.postcode(memberDto.getPostcode())
			.address(memberDto.getAddress())
			.detailAddress(memberDto.getDetailAddress())
			.grade(memberDto.getGrade())
			.birth(memberDto.getBirth())
			.originalProfile(memberDto.getOriginalProfile())
			.renameProfile(memberDto.getRenameProfile())
			.build();
	}

	public int isIdDuplicated(String userID) {
		return sqlSession.selectOne("isIdDuplicated", userID);
	}

	public boolean checkMember(MemberDto memberDto) {

		MemberDto member = sqlSession.selectOne("getMember", memberDto.getUserID());
		String hashPW = member.getUserPW();

		return !hashPW.isEmpty() && BCrypt.checkpw(memberDto.getUserPW(), hashPW);
	}

	public int changeThumbnail(ProfileDto profileDto) {
		return sqlSession.update("changeThumbnail", profileDto);
	}

	public void close() {
		sqlSession.close();
	}
}
