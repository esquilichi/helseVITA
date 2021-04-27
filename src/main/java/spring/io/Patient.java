package spring.io;
import javax.persistence.*;

import java.util.List;

@Entity
@Table
public class Patient extends User{

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToMany
    private List<HealthPersonnel> healthPersonnel;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointment;

    public Patient() {
        super();
    }

    public Patient(String username, String password, String email, String dni) {
       super(username, password, email, dni);
       this.appointment = appointment;
       this.id = id;
    }

    public Integer getId() {
        return id;
    }

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
