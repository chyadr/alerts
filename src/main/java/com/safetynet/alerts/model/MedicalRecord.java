package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "medical_record")
public class MedicalRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JsonIgnoreProperties("medicalRecord")
    private Person person;

    @JsonIgnoreProperties("medicalRecord")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "medical_record_allergy",
            joinColumns = @JoinColumn(name = "medical_record_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();

    @JsonIgnoreProperties("medicalRecord")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "medical_record_medication",
            joinColumns = @JoinColumn(name = "medical_record_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private Set<Medication> medications = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicalRecord id(Integer id){
        this.id = id;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public MedicalRecord person(Person person){
        this.person = person;
        return this;
    }

    public Set<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

    public MedicalRecord allergies(Set<Allergy> allergies){
        this.allergies = allergies;
        return this;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public MedicalRecord medications(Set<Medication> medications){
        this.medications = medications;
        return this;
    }


}



