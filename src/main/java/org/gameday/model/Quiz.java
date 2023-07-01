package org.gameday.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

@RegisterForReflection
public class Quiz {

    public String questionID;
    public String question;

    public String answer1, answer2, answer3, answer4;
    public int correctAnswer;
    //add a new field category
    public String category;

    public Quiz() {}

    public static Quiz from(Map<String, AttributeValue> item) {
        Quiz quiz = new Quiz();
        if (item != null && !item.isEmpty()) {
            quiz.questionID = item.get("questionID").s();
            quiz.question = item.get("question").s();
            quiz.answer1 = item.get("answer1").s();
            quiz.answer2 = item.get("answer2").s();
            quiz.answer3 = item.get("answer3").s();
            quiz.answer4 = item.get("answer4").s();
            quiz.correctAnswer = Integer.parseInt(item.get("correctAnswer").s());
            quiz.category = item.get("category").s();

        }
        return quiz;
    }
    //generate setters and getters for all the fields
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getQuestionID() {
        return questionID;
    }
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public String getAnswer4() {
        return answer4;
    }
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quiz other = (Quiz) obj;
        return Objects.equals(this.questionID, other.questionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.questionID);
    }

}