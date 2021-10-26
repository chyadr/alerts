package com.safetynet.alerts.service.impl;
import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IChildAlertService;
import com.safetynet.alerts.util.CalculateAge;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChildAlertService implements IChildAlertService {

    private final Data data;

    public ChildAlertService(Data data) {
        this.data = data;
    }

    @Override
    public ChildrenAdultsInfoDTO findChildrenAdults(String address) {
        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = new ChildrenAdultsInfoDTO();
        List<Person> persons = data.getPersons().stream().filter(p -> address.equals(p.getAddress())).collect(Collectors.toList());

        // calculate age
        persons.forEach(p -> {
                    Optional<MedicalRecord> mdr = data.getMedicalrecords().stream()
                            .filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())).findFirst();
                    mdr.ifPresent(medicalRecord -> p.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate())));
                });

        childrenAdultsInfoDTO.setChildren(persons.stream().filter(p -> p.getAge() <18).collect(Collectors.toList()));
        childrenAdultsInfoDTO.setAdults(persons.stream().filter(p -> p.getAge() >= 18).collect(Collectors.toList()));

        return childrenAdultsInfoDTO;
    }
}
