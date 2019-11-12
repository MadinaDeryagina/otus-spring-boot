package otus.deryagina.spring.question.loader;

import otus.deryagina.spring.question.exceptions.QuestionsLoadingException;
import otus.deryagina.spring.question.model.Question;

import java.util.List;

public interface QuestionLoader {

    List<Question> loadQuestionsAnswers() throws QuestionsLoadingException;

}
