package otus.deryagina.spring.question;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import otus.deryagina.spring.question.interact.InteractService;

@SpringBootApplication
public class QuestionOnSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QuestionOnSpringBootApplication.class, args);
        try {
            context.getBean(InteractService.class).startInteraction();
        }catch (NoSuchBeanDefinitionException ex){
            System.exit(0);
        }
    }

}
