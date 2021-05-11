package com.urjc.es.helseVITA.Entities;

import javax.persistence.*;

import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity(name = "health_personnel")
@Table(name = "health_personnel")
public class HealthPersonnel {

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
    private String speciality;

    @Column
    private String name;
    @Column
    private String surname1;
    @Column
    private String surname2;
    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    private final EnumRolUsers rol = EnumRolUsers.ROLE_HEALTHPERSONNEL;

    @ManyToMany
    private List<Patient> patients = new ArrayList<>();

    @OneToMany (mappedBy="healthPersonnel",cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public HealthPersonnel(String username, String password, String email, String dni, String name, String surname1, String surname2, Integer age, String speciality) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.speciality=speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public EnumRolUsers getRol() {
        return rol;
    }

    public HealthPersonnel(Integer id, String username, String password, String email, String dni, String speciality,
                           String name, String surname1, String surname2, Integer age, List<Patient> patients) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.speciality = speciality;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.patients = patients;
    }



    public HealthPersonnel(Integer id, String username, String password, String email, String dni, String speciality,
            String name, String surname1, String surname2, Integer age, List<Patient> patients,
            List<Appointment> appointments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.speciality = speciality;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.patients = patients;
        this.appointments = appointments;
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

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " +
                this.surname1 + " "  + this.speciality.toUpperCase(Locale.ROOT);
    }
}
