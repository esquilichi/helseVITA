package spring.io;
<<<<<<< Updated upstream
=======

import javax.persistence.*;
>>>>>>> Stashed changes

import java.util.ArrayList;
import java.util.List;

public class Patient extends User{

    List<Appointment> appointments = new ArrayList<>();


    Patient() {}

    public Patient(String username, String password, String correo, String dni, Long id) {
        super(username, password, correo, dni, id);
    }

<<<<<<< Updated upstream
    public Appointment addAppointment(int hour, int day, int month,int year){
        Appointment temp  = new Appointment(hour, day, month, year);
        this.appointments.add(temp);
        return temp;
=======
    public Patient(String username, String password, String email, String dni) {
        super(username, password, email, dni);
        this.appointment = appointment;
        this.id = id;
>>>>>>> Stashed changes
    }

    public List<Appointment> returnAllAppoinments(){
        return appointments;
    }
<<<<<<< Updated upstream
}
=======

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment; //Esto creo que hay que cambiarlo
    }

}
>>>>>>> Stashed changes
