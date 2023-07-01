package org.gameday.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.gameday.model.Quiz;
import org.gameday.model.Quiz;
import org.gameday.model.Team;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class QuizService {

    @Inject
    DynamoDbClient dynamoDB;
    public List<Quiz> findByAnswer(String answer) {

        List<Quiz> quizes = dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Quiz::from)
                .filter(quiz -> quiz.getAnswer1().equals(answer))
                .filter(quiz -> quiz.getAnswer2().equals(answer))
                .filter(quiz -> quiz.getAnswer3().equals(answer))
                .filter(quiz -> quiz.getAnswer4().equals(answer))


                .collect(Collectors.toList());
        return quizes;
    }
    public List<Quiz> findAll() {
        List<Quiz> quizes = dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Quiz::from)
                .collect(Collectors.toList());
        return quizes;
    }
    public List<Quiz> findByQuestion(String question) {
        List<Quiz> quizes = dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Quiz::from)
                .filter(quiz -> quiz.getQuestion().equals(question))
                .collect(Collectors.toList());
        return quizes;
    }

    public Quiz findByID(String ID) {
        return  Quiz.from(dynamoDB.getItem(getRequest(ID)).item());
    }

    public void addEntry(Quiz quiz) {
        dynamoDB.putItem(putRequest(quiz));
    }
    public static final String QUESTION_ID_COL = "questionID";
    
    public static final String QUESTION_COL = "question";
    public static final String ANSWER1_COL = "answer1";
    public static final String ANSWER2_COL = "answer2";
    public static final String ANSWER3_COL = "answer3";
    public static final String ANSWER4_COL = "answer4";
    public static final String CORRECT_ANSWER_COL = "correctAnswer";

    public static final String CATEGORY_COL = "category";
    


    public String getTableName() {
        return "gameday_quiz";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(QUESTION_ID_COL, QUESTION_COL, ANSWER1_COL, ANSWER2_COL, ANSWER3_COL, ANSWER4_COL, CORRECT_ANSWER_COL, CATEGORY_COL)
                .build();
    }

    protected PutItemRequest putRequest(Quiz quiz) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(QUESTION_ID_COL, AttributeValue.builder().s(quiz.getQuestionID()).build());
        item.put(QUESTION_COL, AttributeValue.builder().s(quiz.getQuestion()).build());
        item.put(ANSWER1_COL, AttributeValue.builder().s(quiz.getAnswer1()).build());
        item.put(ANSWER2_COL, AttributeValue.builder().s(quiz.getAnswer2()).build());
        item.put(ANSWER3_COL, AttributeValue.builder().s(quiz.getAnswer3()).build());
        item.put(ANSWER4_COL, AttributeValue.builder().s(quiz.getAnswer4()).build());
        item.put(CATEGORY_COL, AttributeValue.builder().s(quiz.getCategory()).build());
        item.put(CORRECT_ANSWER_COL, AttributeValue.builder().s("" + quiz.getCorrectAnswer()).build());
        
        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
        
    }
    

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(QUESTION_ID_COL, AttributeValue.builder().s(name).build());
        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(QUESTION_ID_COL, QUESTION_COL, ANSWER1_COL, ANSWER2_COL, ANSWER3_COL, ANSWER4_COL, CORRECT_ANSWER_COL, CATEGORY_COL)
                .build();

    }

}
