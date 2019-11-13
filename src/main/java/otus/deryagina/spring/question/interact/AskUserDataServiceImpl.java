package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;
import otus.deryagina.spring.question.localizer.LocalizationService;


@AllArgsConstructor
@Service
public class AskUserDataServiceImpl implements AskUserDataService {

    private final IOStreamsProvider ioStreamsProvider;

    private final LocalizationService localizationService;

    @Override
    public String getFullName(int numberOfAttempts) {
        ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("enter.fullname", null));
        String fullName = validFullName(numberOfAttempts);
        if (fullName != null) {
            ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("hello.user", new String[]{fullName}));
        }
        return fullName;
    }

    private String validFullName(int numberOfAttempts) {
        for (int i = 0; i < numberOfAttempts; i++) {
            String fullName = ioStreamsProvider.readData();
            if ((StringUtils.isEmpty(fullName) || StringUtils.isBlank(fullName))) {
                if (i != numberOfAttempts - 1) {
                    ioStreamsProvider.printInfo(localizationService.getLocalizedMessage("retry.enter.name",
                            new String[]{String.valueOf(numberOfAttempts - i - 1)}));
                } else {
                    return null;
                }
            } else {
                return fullName;
            }
        }
        return null;
    }

}
