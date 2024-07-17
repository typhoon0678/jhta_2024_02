package com.typhoon0678.board_practice.entity;

import com.typhoon0678.board_practice.dto.AddCommentRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comment(String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public static Comment toEntity(
            AddCommentRequestDto requestDto,
            Board board,
            Member member) {
        return Comment.builder()
                .content(requestDto.getContent())
                .board(board)
                .member(member)
                .build();
    }
}
