package com.safetynet.alerts.dto;

import com.safetynet.alerts.model.Person;

import java.util.List;

public class PersonInfosDTO {

    private long numberOfAdult;
    private long numberOfChild;
    private List<Person> persons;


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public PersonInfosDTO persons(List<Person> persons) {
        this.persons = persons;
        return this;
    }

    public PersonInfosDTO numberOfAdult(long numberOfAdult) {
        this.numberOfAdult = numberOfAdult;
        return this;
    }

    public PersonInfosDTO numberOfChild(long numberOfChild) {
        this.numberOfChild = numberOfChild;
        return this;
    }

    public long getNumberOfAdult() {
        return numberOfAdult;
    }

    public void setNumberOfAdult(long numberOfAdult) {
        this.numberOfAdult = numberOfAdult;
    }

    public long getNumberOfChild() {
        return numberOfChild;
    }

    public void setNumberOfChild(long numberOfChild) {
        this.numberOfChild = numberOfChild;
    }

    @Override
    public String toString() {
        return "PersonInfosDTO{" +
                "numberOfAdult=" + numberOfAdult +
                ", numberOfChild=" + numberOfChild +
                ", persons=" + persons +
                '}';
    }
}
