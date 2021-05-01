package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthPersonnelRepository extends JpaRepository<HealthPersonnel,Integer> {

    public Optional<HealthPersonnel> findHealthPersonnelByDni(String dni);

    public Optional<HealthPersonnel> findHealthPersonnelByUsername(String username);

    public Optional<HealthPersonnel> findHealthPersonnelByEmail(String text);
    
    public List<HealthPersonnel> findHealthPersonnelByNameContains( String healthPersonnelName/*, String surname1, String surname2, String email*/);
	
}
