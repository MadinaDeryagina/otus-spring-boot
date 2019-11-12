package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class InteractServiceImpl implements InteractService {


    private final AskQuestionsService askQuestionsService;
    private final AskUserDataService askUserDataService;

    @Override
    public void startInteraction() {
            final int NUMBER_OF_ATTEMPTS_FOR_FULL_NAME = 3;
            String fullName = askUserDataService.getFullName(NUMBER_OF_ATTEMPTS_FOR_FULL_NAME);
            if( fullName == null){
                System.exit(0);
            }
            askQuestionsService.startAskQuestion();

    }
}
