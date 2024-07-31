package com.typhoon0678.board_practice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boardList;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @Builder
    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
