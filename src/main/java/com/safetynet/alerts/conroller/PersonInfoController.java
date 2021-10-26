package com.safetynet.alerts.conroller;

import com.safetynet.alerts.dto.PersonMedicalRecordDTO;

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


@RestController
@RequestMapping("personInfo")
public class PersonInfoController {
    private static final Logger log = LogManager.getLogger(PersonInfoController.class);
    private final IPersonService personService;

    public PersonInfoController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonMedicalRecordDTO>> findAllByFirstNameAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        log.info("[personInfo] - params [{},{}]", firstName, lastName);

        List<PersonMedicalRecordDTO> personMedicalRecordDTOS = personService.findAllByFirstNameAndLastName(firstName, lastName);
        log.info("[personInfo] - Response {}", personMedicalRecordDTOS.toString());

        return ResponseEntity.status(HttpStatus.OK).body(personMedicalRecordDTOS);
    }
}
