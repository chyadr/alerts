package com.safetynet.alerts.conroller;


import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.MedicalRecordService;
import com.safetynet.alerts.service.impl.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
    private static final Logger log = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private MedicalRecordService medicalRecordService;


    @PostMapping()
    public ResponseEntity<Object> createMedicalRecord(@RequestBody MedicalRecord medicalRecord)
    {
        log.info("[medicalRecord][createMedicalRecord] - params [{}]",medicalRecord.toString());

        if (!StringUtils.hasLength(medicalRecord.getPerson().getFirstName()) || !StringUtils.hasLength(medicalRecord.getPerson().getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        List<Person> persons = personService.findAllByFirstNameAndLastName(medicalRecord.getPerson().getFirstName(),medicalRecord.getPerson().getLastName());

        if (CollectionUtils.isEmpty(persons)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName given are not found !");
        }
        if (persons.size()>1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName given are not unique !");
        }
        // link person to medicalRecord
        medicalRecord.setPerson(persons.get(0));

        MedicalRecord persistedMedicalRecord = medicalRecordService.saveMedicalRecord(medicalRecord);
        log.info("[medicalRecord][createMedicalRecord] - Response {}", persistedMedicalRecord.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(persistedMedicalRecord);
    }

    @PutMapping
    public ResponseEntity<Object> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){

        log.info("[medicalRecord][updateMedicalRecord] - params [{}]",medicalRecord);
        if (medicalRecord.getPerson() == null || !StringUtils.hasLength(medicalRecord.getPerson().getFirstName()) || !StringUtils.hasLength(medicalRecord.getPerson().getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        MedicalRecord persistedMedicalRecord = medicalRecordService.findMedicalRecordByFirstAndLastName(medicalRecord.getPerson().getFirstName(),medicalRecord.getPerson().getLastName());

        if (persistedMedicalRecord == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicalRecord for given FirstName and LastName is not found !");
        }

        BeanUtils.copyProperties(medicalRecord,persistedMedicalRecord,"id","person");
        MedicalRecord updatedMedicalRecord = medicalRecordService.saveMedicalRecord(persistedMedicalRecord);
        log.info("[medicalRecord][updateMedicalRecord] - Response {}", updatedMedicalRecord.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMedicalRecord);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteByFirstAndLastName (@RequestParam(name = "firstName") String firstName,@RequestParam(name = "lastName") String lastName) {
        log.info("[medicalRecord][deleteByFirstAndLastName] - params [{}]",firstName,lastName);

        if (!StringUtils.hasLength(firstName) || !StringUtils.hasLength(lastName)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        MedicalRecord persistedMedicalRecord = medicalRecordService.findMedicalRecordByFirstAndLastName(firstName,lastName);

        if(persistedMedicalRecord == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No MedicalRecord To Be Deleted");
        }
        medicalRecordService.deleteMedicalRecord(persistedMedicalRecord);
        log.info("[medicalRecord][deleteByFirstAndLastName] - Response {}", persistedMedicalRecord.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("MedicalRecord Successfully Deleted");
    }


}



