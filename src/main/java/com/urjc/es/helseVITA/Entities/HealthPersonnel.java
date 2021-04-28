package com.urjc.es.helseVITA.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class HealthPersonnel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String dni;

    @ManyToMany
    private List<Patient> patients;

    @OneToMany
    private List<Appointment> appointments;

    public HealthPersonnel(String username, String password, String email, String dni, List<Patient> patients) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.patients = patients;
    }

    public HealthPersonnel() {

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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
