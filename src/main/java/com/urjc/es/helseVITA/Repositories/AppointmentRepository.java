package com.urjc.es.helseVITA.Repositories;
import java.util.List;
import java.util.Optional;

import com.urjc.es.helseVITA.Entities.Appointment;
import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findAppointmentByYearAndMonthAndDayAndHourAndMinute(Integer year, Integer month, Integer day,Integer hour, Integer minute);
    public Optional<Appointment> findAppointmentsByPatient(Patient patient);
    public Optional<Appointment> findAppointmentsByHealthPersonnel(HealthPersonnel healthPersonnel);
    public List<Appointment> findAppointmentsByPatientId(Integer patient_id);



}
