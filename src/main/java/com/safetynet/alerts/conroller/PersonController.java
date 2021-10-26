package com.safetynet.alerts.conroller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {
    private static final Logger log = LogManager.getLogger(PersonController.class);
    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<Object> createPerson(@RequestBody com.safetynet.alerts.model.Person person) {
        log.info("[person][createPerson] - params [{}]", person.toString());

        if (!StringUtils.hasLength(person.getFirstName()) || !StringUtils.hasLength(person.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        boolean existPerson = personService.existPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName());

        if (existPerson) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are already existed !");
        }

        com.safetynet.alerts.model.Person savedPerson = personService.createPerson(person);
        log.info("[person] - Response {}", savedPerson.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping
    public ResponseEntity<Object> updatePerson(@RequestBody com.safetynet.alerts.model.Person person) {

        log.info("[person][updatePerson] - params [{}]", person.toString());

        if (!StringUtils.hasLength(person.getFirstName()) || !StringUtils.hasLength(person.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        boolean existPerson = personService.existPersonByFirstNameAndLastName(person.getFirstName(),person.getLastName());

        if (!existPerson) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName given are not found !");
        }

        Person updatedPerson = personService.updatePerson(person);
        log.info("[person] [updatePerson] - Response {}", updatedPerson.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPerson);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteByFirstAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        log.info("[person][deleteByFirstAndLastName] - params [{}]", firstName, lastName);

        if (!StringUtils.hasLength(firstName) || !StringUtils.hasLength(lastName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        boolean existPerson = personService.existPersonByFirstNameAndLastName(firstName,lastName);

        if (!existPerson) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Person To Be Deleted");
        }
        personService.deletePerson(firstName,lastName);
        log.info("[person][deleteByFirstAndLastName] - Response {}", firstName + " " + lastName);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Person Successfully Deleted");
    }

}

