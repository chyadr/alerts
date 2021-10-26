package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

public class PersonMedicalRecordDTO {

    private Person person;
    private MedicalRecord medicalRecord;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonMedicalRecordDTO person(Person person) {
        this.person = person;
        return this;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }


    public PersonMedicalRecordDTO medicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
        return this;
    }

    @Override
    public String toString() {
        return "PersonMedicalRecordDTO{" +
                "person=" + person +
                ", medicalRecord=" + medicalRecord +
                '}';
    }
}
