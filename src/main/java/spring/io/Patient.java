package spring.io;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends User{

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name = "patient_ID")
    private Integer patientId;


    private String username;
    private String password;
    private String email;
    private String dni;

    @OneToMany(mappedBy = "Patient")
    private List<Appointment> appointment;

    Patient() {}

    public Patient(String username, String password, String email, String dni) {
        this.username=username;
        this.email=email;
        this.password=password;
        this.dni=dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment; //Esto creo que hay que cambiarlo
    }
    
}
