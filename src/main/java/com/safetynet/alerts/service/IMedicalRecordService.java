package com.safetynet.alerts.service;

public interface IMedicalRecordService {
    com.safetynet.alerts.model.MedicalRecord createMedicalRecord(com.safetynet.alerts.model.MedicalRecord medicalRecord);

    com.safetynet.alerts.model.MedicalRecord updateMedicalRecord(com.safetynet.alerts.model.MedicalRecord medicalRecord);
    void deleteMedicalRecord(String firstName, String lastName);
    boolean existMedicalRecord (String firstName, String lastName);

}
