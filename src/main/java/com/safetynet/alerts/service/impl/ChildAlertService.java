package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.IChildAlertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildAlertService implements IChildAlertService {

    private final PersonRepository personRepository;

    public ChildAlertService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ChildrenAdultsInfoDTO findChildrenAdults(String address) {
        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = new ChildrenAdultsInfoDTO();
        List<Person> persons = personRepository.findPersonsByAddress(address);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());

        childrenAdultsInfoDTO.setChildren(personDTOS.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList()));
        childrenAdultsInfoDTO.setAdults(personDTOS.stream().filter(p -> p.getAge() >= 18).collect(Collectors.toList()));
        return childrenAdultsInfoDTO;
    }
}
