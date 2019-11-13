package otus.deryagina.spring.question.localizer;

public interface LocalizationService {

    String getLocalizedMessage(String key, String[] parameters);

    String getLocalizedQuestionFile();
}
