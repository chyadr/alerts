package com.safetynet.alerts.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

public class MedicalRecord {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date birthdate;
    private Set<String> medications;
    private Set<String> allergies;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public MedicalRecord firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MedicalRecord lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public MedicalRecord birthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(Set<String> medications) {
        this.medications = medications;
    }

    public MedicalRecord medications(Set<String> medications) {
        this.medications = medications;
        return this;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }

    public MedicalRecord allergies(Set<String> allergies) {
        this.allergies = allergies;
        return this;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
