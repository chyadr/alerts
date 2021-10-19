package com.safetynet.alerts.conroller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    private static final Logger log = LogManager.getLogger(PersonController.class);
    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        log.info("[person][createPerson] - params [{}]", person.toString());

        if (!StringUtils.hasLength(person.getFirstName()) || !StringUtils.hasLength(person.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        List<Person> persons = personService.findAllByFirstNameAndLastName(person.getFirstName(), person.getLastName());

        if (!CollectionUtils.isEmpty(persons)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are already existed !");
        }

        Person persistedPerson = personService.createPerson(person);
        log.info("[person] - Response {}", persistedPerson.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(persistedPerson);
    }

    @PutMapping
    public ResponseEntity<Object> updatePerson(@RequestBody Person person) {

        log.info("[person][updatePerson] - params [{}]", person.toString());

        if (!StringUtils.hasLength(person.getFirstName()) || !StringUtils.hasLength(person.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        List<Person> persons = personService.findAllByFirstNameAndLastName(person.getFirstName(), person.getLastName());

        if (CollectionUtils.isEmpty(persons)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("FirstName and LastName given are not found !");
        }
        if (persons.size() > 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName given are not unique !");
        }

        Person updatedPerson = personService.updatePerson(person, persons.get(0));
        log.info("[person] [updatePerson] - Response {}", updatedPerson.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPerson);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteByFirstAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        log.info("[person][deleteByFirstAndLastName] - params [{}]", firstName, lastName);

        if (!StringUtils.hasLength(firstName) || !StringUtils.hasLength(lastName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        List<Person> persons = personService.findAllByFirstNameAndLastName(firstName, lastName);
        if (CollectionUtils.isEmpty(persons)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Person To Be Deleted");
        }
        personService.deletePersons(persons);
        log.info("[person][deleteByFirstAndLastName] - Response {}", persons.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Persons Successfully Deleted");
    }

}

