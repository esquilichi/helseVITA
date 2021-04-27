package spring.io;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class HealthPersonnel extends User{


    private String role;

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Appointment> appointment;

    @ManyToMany(mappedBy="healthPersonnel")
    private List <Patient> patient;


    public HealthPersonnel() {}

    public HealthPersonnel(String username, String password, String email, String dni, Integer id, String role) {
        super(username, password, email, dni, id);
        this.role =role;
    }


    public String getRole() {return role; }

    public void setRole(String role) {
        this.role = role;
    }
}
