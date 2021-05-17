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

    @Column
    private String personName;

    @Column
    private String email;

    @Column
    private String subject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    public Question(String cosa, String answer, String personName, String email, String subjet) {
        this.cosa = cosa;
        this.answer = answer;
        this.personName = personName;
        this.email = email;
        this.subject = subjet;
    }
    
    public Question(String cosa, String answer, String personName, String email, String subject, Integer id) {
        this.cosa = cosa;
        this.answer = answer;
        this.personName = personName;
        this.email = email;
        this.subject = subject;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
}
