package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthPersonnelRepository extends JpaRepository<HealthPersonnel,Integer> {

    public Optional<HealthPersonnel> findHealthPersonnelByDni(String dni);

    public Optional<HealthPersonnel> findHealthPersonnelByUsername(String username);

    public Optional<HealthPersonnel> findHealthPersonnelByEmail(String text);
    //ME PARECE QUE LOS MÉTOOS QUE NECESITEMOS DE FORMA EXTRAÑA SE HACEN SOLOS DE ESTA FORMA, PERO TENGO QUE COMPROBARLO :)
   // public List<HealthPersonnel> findHealthPersonnelByUsername();


}
