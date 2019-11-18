package otus.deryagina.spring.question.questionnaire;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"otus.deryagina.spring.question.questionnaire", "otus.deryagina.spring.question.localizer",
        "otus.deryagina.spring.question.configuration","otus.deryagina.spring.question.loader"})
@EnableConfigurationProperties
@SpringBootConfiguration
public class QuestionServiceImplTestConfiguration {
}
