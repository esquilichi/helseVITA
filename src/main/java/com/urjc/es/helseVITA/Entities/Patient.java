package com.urjc.es.helseVITA.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<HealthPersonnel> healthPersonnelList;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient(){ }

    public Patient(String username, String password, String email, String dni, String name, String surename1, String surename2, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.name = name;
        this.surename1 = surename1;
        this.surename2 = surename2;
        this.age = age;
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