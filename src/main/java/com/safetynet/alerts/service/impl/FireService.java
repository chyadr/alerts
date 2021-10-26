package com.safetynet.alerts.service.impl;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IFireService;
import com.safetynet.alerts.util.CalculateAge;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireService implements IFireService {

    private final Data data;

    public FireService(Data data) {
        this.data = data;
    }

    @Override
    public FireDTO findPersonFireStationNumber(String address) {

        FireDTO fireDTO = new FireDTO();
        List<Person> persons = data.getPersons().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());
        List<FireStation> fireStations= data.getFirestations().stream().filter(f -> f.getAddress().equals(address)).collect(Collectors.toList());
        List<MedicalRecord> medicalRecords=data.getMedicalrecords().stream().filter(m -> persons.stream().anyMatch(p -> p.getFirstName().equals(m.getFirstName()) && (p.getLastName().equals(m.getLastName())))).collect(Collectors.toList());

        // calculate age
        persons.forEach(p -> {
            Optional<MedicalRecord> mdr = medicalRecords.stream()
                    .filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())).findFirst();
            mdr.ifPresent(medicalRecord -> p.setAge(CalculateAge.calculateAge(medicalRecord.getBirthdate())));
        });

        fireDTO.setFireStations(fireStations);
        fireDTO.setPersons(persons);
        fireDTO.setMedicalRecords(medicalRecords);


        return fireDTO;
    }
}