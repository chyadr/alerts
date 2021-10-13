package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMedicalRecordService {
     MedicalRecord saveMedicalRecord( MedicalRecord medicalRecord);
     MedicalRecord findMedicalRecordByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
     void deleteMedicalRecord(MedicalRecord medicalRecord);

}
