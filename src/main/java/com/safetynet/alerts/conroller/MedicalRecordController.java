package com.safetynet.alerts.conroller;


import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
    private static final Logger log = LogManager.getLogger(PersonController.class);
    private final IPersonService personService;
    private final IMedicalRecordService medicalRecordService;

    public MedicalRecordController(IPersonService personService, IMedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping()
    public ResponseEntity<Object> createMedicalRecord(@RequestBody com.safetynet.alerts.model.MedicalRecord medicalRecord) {
        log.info("[medicalRecord][createMedicalRecord] - params [{}]", medicalRecord.toString());

        if (!StringUtils.hasLength(medicalRecord.getFirstName()) || !StringUtils.hasLength(medicalRecord.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        boolean existMedicalRecord = medicalRecordService.existMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());

        if (existMedicalRecord) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName given are already exist for a medical record !");
        }


        MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        log.info("[medicalRecord][createMedicalRecord] - Response {}", createdMedicalRecord.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalRecord);
    }

    @PutMapping
    public ResponseEntity<Object> updateMedicalRecord(@RequestBody com.safetynet.alerts.model.MedicalRecord medicalRecord) {

        log.info("[medicalRecord][updateMedicalRecord] - params [{}]", medicalRecord);
        if (!StringUtils.hasLength(medicalRecord.getFirstName()) || !StringUtils.hasLength(medicalRecord.getLastName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }
        boolean existMedicalRecord = medicalRecordService.existMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());

        if (!existMedicalRecord) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MedicalRecord for given FirstName and LastName is not found !");
        }

        com.safetynet.alerts.model.MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
        log.info("[medicalRecord][updateMedicalRecord] - Response {}", updatedMedicalRecord.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMedicalRecord);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteByFirstAndLastName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        log.info("[medicalRecord][deleteByFirstAndLastName] - params [{}]", firstName, lastName);

        if (!StringUtils.hasLength(firstName) || !StringUtils.hasLength(lastName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FirstName and LastName are required !");
        }

        boolean existMedicalRecord = medicalRecordService.existMedicalRecord(firstName, lastName);

        if (!existMedicalRecord) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No MedicalRecord To Be Deleted");
        }
        medicalRecordService.deleteMedicalRecord(firstName,lastName);
        log.info("[medicalRecord][deleteByFirstAndLastName] - Response {}", firstName + " " +lastName);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("MedicalRecord Successfully Deleted");
    }


}



