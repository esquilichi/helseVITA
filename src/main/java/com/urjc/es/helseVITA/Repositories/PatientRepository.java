package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Integer>, JpaSpecificationExecutor<Patient> {

    public Optional<Patient> findByUsername(String username);

    public Optional<Patient> findByEmail(String email);

    public Optional<Patient> findByDni(String dni);

    public Collection<Patient> findPatientByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCase(String input, String input1, String input2, String input3);

    public List<Patient> findPatientByNameContainsIgnoreCaseOrSurname1ContainsIgnoreCaseOrSurname2ContainsIgnoreCaseOrEmailContainsIgnoreCaseOrAgeContains(String name, String surname1, String surname2, String email, Integer age);

    public List<Patient> findPatientByAge(Integer age);

    public List<Patient> findPatientByHealthPersonnelList(HealthPersonnel healthPersonnel);
}
