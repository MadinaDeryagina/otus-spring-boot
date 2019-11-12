package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;
import otus.deryagina.spring.question.localizer.LocalizationService;
import otus.deryagina.spring.question.model.Question;
import otus.deryagina.spring.question.questionnaire.QuestionService;

import java.util.List;

@Service
@AllArgsConstructor
public class AskQuestionsServiceImpl implements AskQuestionsService {

    private final QuestionService questionService;
    private final IOStreamsProvider ioStreamsProvider;
    private final LocalizationService localizationService;

    @Override
    public void startAskQuestion() {

        List<Question> questions = questionService.getQuestions();
        ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("number.questions.to.ask", new String[]{String.valueOf(questions.size())}));
        int counter = 0;
        for (Question question : questions) {
            ioStreamsProvider.printInfo(question.getQuestion());
            String answer = ioStreamsProvider.readData();
            boolean isCorrectAnswer = questionService.isCorrectAnswer(question.getQuestion(), answer);
            if (isCorrectAnswer) {
                ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("correct.answer", null));
                counter++;
            } else {
                ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("wrong.answer", null));
            }
        }
        ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("user.result",
                new String[]{String.valueOf(counter), String.valueOf(questions.size())}));

    }
}
