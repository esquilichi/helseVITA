package com.urjc.es.helseVITA.Repositories;
import java.util.List;

import com.urjc.es.helseVITA.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    public List<Appointment> findAppointmentByYearAndMonthAndDayAndHourAndMinute(Integer year, Integer month, Integer day,Integer hour, Integer minute);


}
