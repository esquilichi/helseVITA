package com.urjc.es.helseVITA.Repositories;

import com.urjc.es.helseVITA.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
