package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

}
