package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public Optional<Patient> findByUsername(String username);

    public Optional<Patient> findByEmail(String email);

    public Optional<Patient> findByDni(String dni);
}
