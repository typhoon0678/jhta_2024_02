package com.typhoon0678.board_practice.service;

import com.typhoon0678.board_practice.entity.Board;
import com.typhoon0678.board_practice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }

    public Page<Board> getBoardIndexPage(int page) {
        Pageable pageable = PageRequest.of(page, 2);

        return boardRepository.findAll(pageable);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Board not found"));
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
