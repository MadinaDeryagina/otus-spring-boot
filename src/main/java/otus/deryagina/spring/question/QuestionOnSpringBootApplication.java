package otus.deryagina.spring.question;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import otus.deryagina.spring.question.interact.InteractService;


@SpringBootApplication
public class QuestionOnSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuestionOnSpringBootApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(InteractService.class)
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            InteractService interactService=ctx.getBean(InteractService.class);
            interactService.startInteraction();
        };
    }

}
