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

    @Column
    private String name;
    @Column
    private String surename1;
    @Column
    private String surename2;
    @Column
    private Integer age;

    @ManyToMany
    private List<Patient> patients;

    @OneToMany
    private List<Appointment> appointments;

    public HealthPersonnel(String username, String password, String email, String dni, List<Patient> patients, String name, String surename1, String surename2, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.patients = patients;
        this.name = name;
        this.surename1 = surename1;
        this.surename2 = surename2;
        this.age = age;
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename1() {
        return surename1;
    }

    public void setSurename1(String surename1) {
        this.surename1 = surename1;
    }

    public String getSurename2() {
        return surename2;
    }

    public void setSurename2(String surename2) {
        this.surename2 = surename2;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
}
