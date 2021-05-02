package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public Optional<Patient> findByUsername(String username);

    public Optional<Patient> findByEmail(String email);

    public Optional<Patient> findByDni(String dni);

    public Collection<Patient> findHealthPersonnelByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCase(String input, String input1, String input2, String input3);
}
