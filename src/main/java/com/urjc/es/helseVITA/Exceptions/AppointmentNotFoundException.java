package com.urjc.es.helseVITA.Exceptions;


import com.urjc.es.helseVITA.Entities.Appointment;

public class AppointmentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AppointmentNotFoundException() {
        super("No hay citas");
    }

}

