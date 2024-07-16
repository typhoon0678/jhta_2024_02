package com.typhoon0678.question.repository;

import com.typhoon0678.question.enitty.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
