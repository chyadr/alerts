package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.AddressDTO;
import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IFloodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class FloodService implements IFloodService {

    private final PersonRepository personRepository;

    public FloodService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<AddressPersonsMedicalRecordDTO> findAddressPersonsMedicalRecords(List<Integer> stations) {

        List<Person> persons = personRepository.findPersonsByStations(stations);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());
        Map<AddressDTO, Set<PersonDTO>> map = personDTOS.stream().collect(Collectors.groupingBy(PersonDTO::getAddressDTO, TreeMap::new, Collectors.toSet()));

        List<AddressPersonsMedicalRecordDTO> addressPersonsMedicalRecordDTOS = map.entrySet().stream().map(m -> {
            AddressPersonsMedicalRecordDTO adr = new AddressPersonsMedicalRecordDTO();
            adr.setPersonDTOS(m.getValue());
            adr.setAddressDTO(m.getKey());
            return adr;
        }).collect(Collectors.toList());
        return addressPersonsMedicalRecordDTOS;
    }
}
