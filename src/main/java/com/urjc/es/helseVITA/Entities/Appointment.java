package com.urjc.es.helseVITA.Entities;

import javax.persistence.*;

@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer hour;
    private Integer day;
    private Integer month;
    private Integer year;

    @ManyToOne
    private HealthPersonnel healthPersonnel;

    @ManyToOne
    private Patient patient;

    public Appointment(){}

    public Appointment(Integer hour, Integer day, Integer month, Integer year, HealthPersonnel healthPersonnel, Patient patient) {
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.healthPersonnel = healthPersonnel;
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public HealthPersonnel getHealthPersonnel() {
        return healthPersonnel;
    }

    public void setHealthPersonnel(HealthPersonnel healthPersonnel) {
        this.healthPersonnel = healthPersonnel;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}