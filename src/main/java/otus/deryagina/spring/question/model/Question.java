package otus.deryagina.spring.question.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;


@Getter
@ToString
public class Question {

    private final String question;
    private final String correctAnswer;

    @JsonCreator
    public Question(@JsonProperty("question") String question,@JsonProperty("correct_answer") String correctAnswer) {
        if (!isValidInput(question, correctAnswer)) {
            throw new IllegalArgumentException("invalid input to construct Question");
        }
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
    private boolean isValidInput(String question, String correctAnswer){
        return isValidString(question)&&isValidString(correctAnswer);
    }
    private boolean isValidString(String inputString){
        return inputString!=null && !StringUtils.isEmpty(inputString) && !StringUtils.isBlank(inputString);
    }

}
