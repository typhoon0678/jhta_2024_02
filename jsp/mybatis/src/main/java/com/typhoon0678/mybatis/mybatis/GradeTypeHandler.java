package com.typhoon0678.mybatis.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.typhoon0678.mybatis.dto.Grade;

@MappedTypes(Grade.class)
public class GradeTypeHandler implements TypeHandler<Grade> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Grade parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getLabel());
	}

	@Override
	public Grade getResult(ResultSet rs, String columnName) throws SQLException {
		return returnGrade(rs.getString(columnName));
	}

	@Override
	public Grade getResult(ResultSet rs, int columnIndex) throws SQLException {
		return returnGrade(rs.getString(columnIndex));
	}

	@Override
	public Grade getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return returnGrade(cs.getString(columnIndex));
	}

	private Grade returnGrade(String grade) {
		for (Grade g : Grade.values()) {
			if (grade.equalsIgnoreCase(g.getLabel())) {
				return g;
			}
		}

		return null;
	}
}
