package com.typhoon0678.board_practice.service;

import com.typhoon0678.board_practice.dto.BoardDto;
import com.typhoon0678.board_practice.entity.Board;
import com.typhoon0678.board_practice.entity.Member;
import com.typhoon0678.board_practice.repository.BoardRepository;
import com.typhoon0678.board_practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void write(BoardDto boardDto, String username) {

        Member member = memberRepository.findByUsername(username);

        boardRepository.save(Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .member(member).build());
    }

    public List<Board> getBoardIndex() {
        return boardRepository.findAll();
    }
}
