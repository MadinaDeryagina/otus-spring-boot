package otus.deryagina.spring.question.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import otus.deryagina.spring.question.interact.AskQuestionsService;
import otus.deryagina.spring.question.interact.AskUserDataService;
import otus.deryagina.spring.question.localizer.LocalizationService;

@ShellComponent
@RequiredArgsConstructor
public class QuestionApplicationCommands {

    private final AskUserDataService askUserDataService;
    private final AskQuestionsService askQuestionsService;
    private final LocalizationService localizationService;

    private String fullName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public void login() {
        final int NUMBER_OF_ATTEMPTS_FOR_FULL_NAME = 3;
        String fullName = askUserDataService.getFullName(NUMBER_OF_ATTEMPTS_FOR_FULL_NAME);
        if (fullName != null) {
            this.fullName = fullName;
        }
    }

    @ShellMethod(value = "Start ask question", key = {"saq"})
    @ShellMethodAvailability(value = "isStartAskQuestionsCommandAvailable")
    public void startAskQuestions() {
        askQuestionsService.startAskQuestion();
    }

    private Availability isStartAskQuestionsCommandAvailable() {
        return fullName == null ?
                Availability.unavailable(localizationService.getLocalizedMessage("please.log.in", null))
                : Availability.available();
    }
}
