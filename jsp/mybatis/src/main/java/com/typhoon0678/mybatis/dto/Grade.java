package com.typhoon0678.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {

	MEMBER("member"),
	ADMIN("admin"),
	MANAGER("manager");

	private final String label;
}
