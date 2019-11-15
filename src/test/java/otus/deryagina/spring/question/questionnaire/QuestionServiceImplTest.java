package otus.deryagina.spring.question.questionnaire;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@DisplayName("question service")
class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;


    @Test
    @DisplayName("must return 1 question on test data")
    void getQuestions() {
        assertThat(questionService.getQuestions().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("must correct accept russian answer")
    void isCorrectAnswer() {
        assertThat(questionService.isCorrectAnswer(questionService.getQuestions().get(0).getQuestion(),"Лондон"))
        .isEqualTo(true);
    }
}