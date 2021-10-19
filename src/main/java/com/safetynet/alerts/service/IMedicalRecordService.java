package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import org.springframework.data.repository.query.Param;

public interface IMedicalRecordService {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, MedicalRecord persistedMedicalRecord);

    MedicalRecord findMedicalRecordByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    void deleteMedicalRecord(MedicalRecord medicalRecord);

}
