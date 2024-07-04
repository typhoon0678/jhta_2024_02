package com.typhoon0678.board.service;

import com.typhoon0678.board.dao.BoardDao;
import com.typhoon0678.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao boardDao;

    public int writeBoard(BoardDto boardDto) {
        return boardDao.writeBoard(boardDto);
    }
}
