package com.urjc.es.helseVITA.Exceptions;


import com.urjc.es.helseVITA.Entities.Appointment;

public class AppointmentNotFoundException extends RuntimeException{
    Appointment appointment;
    private static final long serialVersionUID = 1L;

    public AppointmentNotFoundException(Appointment appointment) {
        super("No se ha encontrado la cita: " + appointment);
        this.appointment = appointment;
    }


    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}

