package com.safetynet.alerts.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private Integer zip;
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "address")
    @JsonIgnoreProperties("address")
    private Set<Person> persons = new HashSet<>();
    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties("address")
    private FireStation fireStation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address id(Integer id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address address(String address) {
        this.address = address;
        return this;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Address zip(Integer zip) {
        this.zip = zip;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public FireStation getFireStation() {
        return fireStation;
    }

    public void setFireStation(FireStation fireStation) {
        this.fireStation = fireStation;
    }

    public Address fireStation(FireStation fireStation) {
        this.fireStation = fireStation;
        return this;
    }

}
