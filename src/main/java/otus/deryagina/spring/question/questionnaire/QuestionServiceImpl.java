package otus.deryagina.spring.question.questionnaire;


import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.exceptions.QuestionsLoadingException;
import otus.deryagina.spring.question.loader.QuestionLoader;
import otus.deryagina.spring.question.localizer.LocalizationService;
import otus.deryagina.spring.question.model.Question;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Map<String,Question> mapOfQuestions;
    private final LocalizationService localizationService;

    public QuestionServiceImpl(LocalizationService localizationService, QuestionLoader questionLoader) throws QuestionsLoadingException {
        this.localizationService = localizationService;
        List<Question> questionList = questionLoader.loadQuestionsAnswers();
        mapOfQuestions= new HashMap<>();
        for (Question currentQuestion :questionList ) {
            mapOfQuestions.put(currentQuestion.getQuestion(),currentQuestion);
        }
    }

    @Override
    public List<Question> getQuestions() {
        if(mapOfQuestions.isEmpty()){
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(new ArrayList<>(mapOfQuestions.values()));
    }

    @Override
    public boolean isCorrectAnswer(String question, String answer){
        if( !mapOfQuestions.containsKey(question)){
            throw new IllegalArgumentException(localizationService.getLocalizedMessage("no.such.question",
                    new String[]{question}));
        }
        return answer.equalsIgnoreCase(mapOfQuestions.get(question).getCorrectAnswer());
    }
}
