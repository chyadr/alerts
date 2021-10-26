package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPhoneAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService implements IPhoneAlertService {

    private final Data data;

    public PhoneAlertService(Data data) {
        this.data = data;
    }

    @Override
    public List<String> findPhoneNumberByFireStationNumber (Integer stationNumber) {
        return data.getPersons().stream().filter(p -> data.getFirestations().stream()
                .anyMatch(f -> f.getAddress().equals(p.getAddress()) && f.getStation().equals(stationNumber))).map(Person::getPhone).collect(Collectors.toList());

    }

}
