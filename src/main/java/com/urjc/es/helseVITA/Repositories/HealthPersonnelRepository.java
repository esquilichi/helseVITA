package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthPersonnelRepository extends JpaRepository<HealthPersonnel,Integer> {

    public List<HealthPersonnel> findHealthPersonnelByDni(String dni);

    public List<HealthPersonnel> findHealthPersonnelByUsername(String username);

    //ME PARECE QUE LOS MÉTOOS QUE NECESITEMOS DE FORMA EXTRAÑA SE HACEN SOLOS DE ESTA FORMA, PERO TENGO QUE COMPROBARLO :)
   // public List<HealthPersonnel> findHealthPersonnelByUsername();


}
