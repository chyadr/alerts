package com.safetynet.alerts.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fire_station")
public class FireStation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "station")
    private Integer station;

    @OneToOne
    @JsonIgnoreProperties("fireStation")
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FireStation id(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public FireStation station(Integer station) {
        this.station = station;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FireStation address(Address address) {
        this.address = address;
        return this;
    }

}
