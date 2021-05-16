package com.urjc.es.helseVITA.Controllers;


import com.urjc.es.helseVITA.Entities.Question;
import com.urjc.es.helseVITA.Repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsController {

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping("/api/preguntas")
    public Question agregarPregunta(@RequestBody Question question){
        return questionRepository.save(question);
    }

}
