package otus.deryagina.spring.question.localizer;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.configuration.ApplicationSettings;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final ApplicationSettings applicationSettings;
    private final Locale locale;
    private final String localizedFileName;

    public LocalizationServiceImpl(MessageSource messageSource, ApplicationSettings applicationSettings) {
        this.messageSource = messageSource;
        this.applicationSettings = applicationSettings;
        String localeName = this.applicationSettings.getLocaleName();
        this.locale = new Locale(localeName);
        localizedFileName = this.applicationSettings.getQuestionBaseFilename() + "_" + localeName + ".csv";
    }

    @Override
    public String getLocalizedMessage(String key, String[] parameters) {
        return messageSource.getMessage(key, parameters, locale);
    }

    @Override
    public String getLocalizedQuestionFile() {
        return localizedFileName;
    }
}