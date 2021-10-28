package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.util.CalculateAge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService implements IFireStationService {

    private final Data data;

    public FireStationService(Data data) {
        this.data = data;
    }

    @Override
    public FireStation createFireStation(FireStation fireStation) {
        data.getFirestations().add(fireStation);
        return fireStation;
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        data.getFirestations().forEach(f -> {
            if (f.getAddress().equals(fireStation.getAddress())){
                f.setStation(fireStation.getStation());
            }
        });

        return fireStation;
    }

    @Override
    public void deleteFireStation(String address) {
        data.getFirestations().removeIf(f -> f.getAddress().equals(address));
    }


    @Override
    public boolean existFireStationsByAddress(String address) {
        return data.getFirestations().stream().anyMatch(f -> f.getAddress().equals(address));
    }

    @Override
    public PersonInfosDTO findPersonsByStationNumber(Integer stationNumber) {
        PersonInfosDTO personInfosDTO = new PersonInfosDTO();
        List<Person> persons = data.getPersons().stream().
                filter(p -> data.getFirestations().stream().anyMatch(f -> f.getStation().equals(stationNumber)
                        && f.getAddress().equals(p.getAddress()))).collect(Collectors.toList());
        personInfosDTO.setPersons(persons);
        // calculate age
        personInfosDTO.setNumberOfAdult((persons.stream().filter(p -> data.getMedicalrecords().stream()
                .anyMatch(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())
                        && CalculateAge.calculateAge(m.getBirthdate()) < 18 )).count()));
        personInfosDTO.setNumberOfChild(persons.size() - personInfosDTO.getNumberOfAdult());
        return personInfosDTO;
    }


}





