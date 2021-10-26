package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.model.Data;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IFloodService;
import com.safetynet.alerts.util.CalculateAge;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FloodService implements IFloodService {
    private final Data data;

    public FloodService(Data data) {
        this.data = data;
    }

    @Override
    public Map<String,List<PersonMedicalRecordDTO>> findAddressPersonsMedicalRecords(List<Integer> stations) {

        List<FireStation> fireStations = data.getFirestations().stream().filter(f->stations.contains(f.getStation())).collect(Collectors.toList());
        List<Person> persons = data.getPersons().stream().filter(p-> fireStations.stream().anyMatch(f->f.getAddress().equals(p.getAddress()))).collect(Collectors.toList());
        List<MedicalRecord> medicalRecords = data.getMedicalrecords().stream().filter(m->persons.stream()
                .anyMatch(p->p.getFirstName().equals(m.getFirstName()) && p.getLastName().equals(m.getLastName()))).collect(Collectors.toList());

        // calculate age
        persons.forEach(p -> {
            Optional<MedicalRecord> mdr = medicalRecords.stream()
                    .filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())).findFirst();
            mdr.ifPresent(medicalRecord -> p.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate())));
        });

        Map<String,List<PersonMedicalRecordDTO>> map=persons.stream().map(p -> {
            PersonMedicalRecordDTO pm = new PersonMedicalRecordDTO();
            pm.setPerson(p);
            pm.setMedicalRecord(medicalRecords.stream().filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())).findFirst().orElseGet(null));
            return pm;
        }).collect(Collectors.groupingBy(pm -> pm.getPerson().getAddress(), Collectors.toList()));


        return  map;
    }
}