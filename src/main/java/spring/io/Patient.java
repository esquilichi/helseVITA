package spring.io;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User{

    List<Appointment> appointments = new ArrayList<>();


    Patient() {}

    public Patient(String username, String password, String correo, String dni, Long id) {
        super(username, password, correo, dni, id);
    }

    public Appointment addAppointment(int hour, int month,int year){
        Appointment temp  = new Appointment(hour, month, year);
        this.appointments.add(temp);
        return temp;
    }

    public List<Appointment> returnAllAppoinments(){
        return appointments;
    }
}
