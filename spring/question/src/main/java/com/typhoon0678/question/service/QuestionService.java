package com.typhoon0678.question.service;

import com.typhoon0678.question.enitty.Question;
import com.typhoon0678.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void insert(String subject, String content) {
        questionRepository.save(Question.builder()
                .subject(subject)
                .content(content)
                .regDate(LocalDateTime.now())
                .build());
    }
}
