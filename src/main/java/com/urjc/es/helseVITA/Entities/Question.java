package com.urjc.es.helseVITA.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    
    @Column(nullable = false)
    private String cosa;

    @Column
    private String answer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Question(String cosa, String answer){
        this.cosa = cosa;
        this.answer=answer;
    }
    public Question(String cosa, String answer, Integer id){
        this.cosa = cosa;
        this.answer=answer;
        this.id = id;
    }
    public Question(){
        
    }
    public String getCosa() {
        return this.cosa;
    }

    public void setCosa(String cosa) {
        this.cosa = cosa;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
}
