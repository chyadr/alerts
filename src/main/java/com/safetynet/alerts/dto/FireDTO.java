package com.safetynet.alerts.dto;



import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import java.util.List;

public class FireDTO {

    private List<Person> persons;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
