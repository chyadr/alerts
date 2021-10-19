package com.safetynet.alerts.conroller;

import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("personInfo")
public class PersonInfoController {
    private static final Logger log = LogManager.getLogger(PersonMapper.class);
    private final IPersonService personService;

    public PersonInfoController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAllByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        log.info("[personInfo] - params [{},{}]", firstName, lastName);

        List<PersonDTO> personDTOS = personService.findAllByFirstNameAndLastName(firstName, lastName).stream().map(PersonMapper::mapPerson).collect(Collectors.toList());
        log.info("[personInfo] - Response {}", personDTOS.toString());

        return ResponseEntity.status(HttpStatus.OK).body(personDTOS);
    }
}
