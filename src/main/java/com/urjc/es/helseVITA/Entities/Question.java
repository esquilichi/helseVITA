package com.urjc.es.helseVITA.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    
    private String cosa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Question(String cosa){
        this.cosa = cosa;
    }
    public Question(String cosa, Integer id){
        this.cosa = cosa;
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
    
}
