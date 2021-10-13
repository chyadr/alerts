package com.safetynet.alerts.dto;

import java.util.List;

public class PersonInfosDTO {

    private long numberOfAdult;
    private long numberOfChild;
    private List<PersonDTO> personDTOS;


    public List<PersonDTO> getPersonDTOS() {
        return personDTOS;
    }

    public void setPersonDTOS(List<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
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
                ", personDTOS=" + personDTOS +
                '}';
    }
}
