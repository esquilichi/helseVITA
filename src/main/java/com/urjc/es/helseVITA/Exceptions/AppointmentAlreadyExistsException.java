package com.urjc.es.helseVITA.Exceptions;

import com.urjc.es.helseVITA.Entities.Appointment;

public class AppointmentAlreadyExistsException extends RuntimeException{

    Appointment appointment;
    private static final long serialVersionUID = 1L;

    public AppointmentAlreadyExistsException(Appointment appointment){
        super("La cita " + appointment + "ya existe");
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
