package com.typhoon0678.board_test.repository;

import com.typhoon0678.board_test.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
