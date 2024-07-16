package com.typhoon0678.board_practice.repository;

import com.typhoon0678.board_practice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
