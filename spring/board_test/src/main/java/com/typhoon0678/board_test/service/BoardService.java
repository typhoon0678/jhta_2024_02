package com.typhoon0678.board_test.service;

import com.typhoon0678.board_test.domain.Board;
import com.typhoon0678.board_test.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board) {
        boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(int id) {
        return boardRepository.findById((long) id).orElse(null);
    }

    public void delete(int id) {
        boardRepository.deleteById((long) id);
    }
}
