package org.gameday.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.gameday.model.Quiz;
import org.gameday.service.QuizService;

import java.util.List;

@Path("/quiz")
public class QuizResource {

    @Inject
    QuizService service;

    @GET
    @Path("/findAll")
    public List<Quiz> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/find/{quizID}")
    public Quiz findByID(String ID) {
        return service.findByID(ID);
    }
    //find by description
    @GET
    @Path("/findByQuestion/{question}")
    public List<Quiz> findByQuestion(String question) {
        return service.findByQuestion(question);
    }
    @GET
    @Path("/findByAnswer/{answer}")
    public List<Quiz> findByAnswer(String answer) {
        return service.findByAnswer(answer);


    }


    @POST
    public void addEntry(Quiz quiz) throws Exception {
        service.addEntry(quiz);
    }



}

