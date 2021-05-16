package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    
}
