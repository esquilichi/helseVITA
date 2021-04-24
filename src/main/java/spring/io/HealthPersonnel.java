package spring.io;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HealthPersonnel extends User{

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private String role;

    public HealthPersonnel() {}

    public HealthPersonnel(String username, String password, String email, String dni, Long id, String role) {
        super(username, password, email, dni, id);
        this.role =role;
    }

    public String getUsername(){return getUsername();}

    public String getEmail(){return getEmail();}

    public String getDni (){return getDni();}

    public Long getId(){return getId();}

    public String getRole() {return role; }

    public void setRole(String role) {
        this.role = role;
    }
}
