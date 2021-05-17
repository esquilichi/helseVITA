package com.urjc.es.helseVITA.Entities;

import javax.persistence.*;

import com.urjc.es.helseVITA.Enums.EnumRolUsers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Patient implements UserDetails{
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
    private String surname1;
    @Column
    private String surname2;
    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    private final EnumRolUsers rol = EnumRolUsers.ROLE_PATIENT;

    @ManyToMany
    private List<HealthPersonnel> healthPersonnelList = new ArrayList<>();

    @OneToMany(mappedBy="patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public Patient(){ }

    public Patient(String username, String password, String email, String dni, String name, String surname1, String surname2, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
    }

    

    public Patient(Integer id, String username, String password, String email, String dni, String name, String surname1,
            String surname2, Integer age, List<HealthPersonnel> healthPersonnelList, List<Appointment> appointments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.healthPersonnelList = healthPersonnelList;
        this.appointments = appointments;
    }

    public Patient(Integer id, String username, String password, String email, String dni, String name, String surname1,
            String surname2, Integer age, List<HealthPersonnel> healthPersonnelList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.age = age;
        this.healthPersonnelList = healthPersonnelList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(rol.toString()));
        return roles;
    }

    @Override
    public String toString(){
        return this.name + " " + this.surname1;
    }

    @Override
    public boolean equals(Object patient){
        if (patient == this)
            return true;
        if (!(patient instanceof Patient))
            return false;
        return this.username.equals(((Patient) patient).username);
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public EnumRolUsers getRol() {
        return rol;
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

    public List<HealthPersonnel> getHealthPersonnelList() {
        return healthPersonnelList;
    }

    public void setHealthPersonnelList(List<HealthPersonnel> healthPersonnelList) {
        this.healthPersonnelList = healthPersonnelList;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getPasswordHash() {
        return null;
    }
}
