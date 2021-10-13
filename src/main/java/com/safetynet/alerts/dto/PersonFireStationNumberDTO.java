package com.safetynet.alerts.dto;



import java.util.List;

public class PersonFireStationNumberDTO {

    private Integer stationNumber ;
    private List<PersonDTO> personDTOS;

    public List<PersonDTO> getPersonDTOS() {
        return personDTOS;
    }

    public void setPersonDTOS(List<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
    }

    public Integer getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    @Override
    public String toString() {
        return "PersonFireStationNumberDTO{" +
                "stationNumber=" + stationNumber +
                ", personDTOS=" + personDTOS +
                '}';
    }
}
