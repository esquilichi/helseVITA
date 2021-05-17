package com.urjc.es.helseVITA.Controllers;



import java.util.List;


import com.urjc.es.helseVITA.Entities.Question;
import com.urjc.es.helseVITA.Services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class QuestionsController {

    @Autowired
    QuestionService questionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/preguntas")
    public Question agregarPregunta(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/api/preguntas")
    public List <Question> returnPreguntas(){
        return questionService.returnAll();
    }
    @PutMapping("/api/preguntas/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question question){
        if (questionService.exists(id)){
            return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/preguntas/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Integer id){
        if (questionService.exists(id)){
            questionService.delete(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get one specified user
    @GetMapping("/api/question/{id}")
    public ResponseEntity<Question> getSinglePatient(@PathVariable Integer id){
        if (questionService.exists(id)){
            return new ResponseEntity<>(questionService.returnQuestion(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/api/preguntas/{id}")
    public ResponseEntity<Question> patchPatient(@PathVariable Integer id, @RequestBody Question question){
        if (questionService.exists(id)){
            //Get actual Patient with that ID
            Question temp = questionService.returnQuestion(id);

            if (question.getCosa() != null)
                temp.setCosa(question.getCosa());
            if(question.getAnswer() != null)
                temp.setAnswer(question.getAnswer());
            if(question.getEmail() != null)
                temp.setEmail(question.getEmail());
            if(question.getPersonName() != null)
                temp.setPersonName(question.getPersonName());
            if(question.getSubject() != null)
                temp.setSubject(question.getSubject());
           

            return new ResponseEntity<>(questionService.addQuestion(temp),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
