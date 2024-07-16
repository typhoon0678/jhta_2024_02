package com.typhoon0678.question;

import com.typhoon0678.question.enitty.Question;
import com.typhoon0678.question.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QuestionTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void insertQuestion() {
        Question question = new Question(
                1,
                "test question",
                "test content",
                LocalDateTime.now());

        questionRepository.save(question);

    }

    @Test
    public void getAllTest() {
        List<Question> questionList = questionRepository.findAll();
        Question question = questionList.get(0);

        Assertions.assertThat(question.getSubject())
                .isEqualTo("test question");
    }
}
