package com.typhoon0678.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

	private int no;
	private String subject;
	private String content;
	private String userID;
	private String userName;
	private int reGroup;
	private int reLevel;
	private int reStep;
	private String regDate;
	private int hit;
	private int available;

}
