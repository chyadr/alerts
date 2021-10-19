package com.safetynet.alerts.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medication id(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Medication name(String name) {
        this.name = name;
        return this;
    }
}
