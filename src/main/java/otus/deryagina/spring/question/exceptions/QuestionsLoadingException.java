package otus.deryagina.spring.question.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionsLoadingException extends Exception {
    private String message;
}
