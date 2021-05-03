package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface HealthPersonnelRepository extends JpaRepository<HealthPersonnel,Integer>, JpaSpecificationExecutor<HealthPersonnel> {

    public Optional<HealthPersonnel> findHealthPersonnelByDni(String dni);

    public Optional<HealthPersonnel> findHealthPersonnelByUsername(String username);

    public Optional<HealthPersonnel> findHealthPersonnelByEmail(String text);

    
    public List<HealthPersonnel> findHealthPersonnelByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCase( String healthPersonnelName, String surname1, String surname2, String email);
	
    public List<HealthPersonnel> findHealthPersonnelByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCaseOrAgeContains(String name, String surname1, String surname2, String email, Integer age);

    public List<HealthPersonnel> findByAge(Integer input);

    public List<HealthPersonnel> findHealthPersonnelsByPatientsIn(List<Patient> patients);
}
