package com.typhoon0678.board.dao;

import com.typhoon0678.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {

    int writeBoard(BoardDto boardDto);
}
