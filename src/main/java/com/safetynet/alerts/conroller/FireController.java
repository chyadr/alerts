package com.safetynet.alerts.conroller;


import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.dto.PersonFireStationNumberDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("fire")
public class FireController {
    private static final Logger log = LogManager.getLogger(FireController.class);
    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<PersonFireStationNumberDTO> findPersonsByStationNumber(@RequestParam(name = "address") String address) {
        log.info("[fire] - params [{}]",address);
        PersonFireStationNumberDTO personFireStationNumberDTO = new PersonFireStationNumberDTO();
        List<Person> persons = personService.findPersonsByAddress(address);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());

        personFireStationNumberDTO.setStationNumber(persons.stream().findFirst().get().getAddress().getFireStation().getStation());
        personFireStationNumberDTO.setPersonDTOS(personDTOS);

        log.info("[fire] - Response {}", personFireStationNumberDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(personFireStationNumberDTO);
    }
}