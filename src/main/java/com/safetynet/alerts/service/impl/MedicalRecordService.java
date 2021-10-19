package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.IMedicalRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService implements IMedicalRecordService {


    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, MedicalRecord persistedMedicalRecord) {
        BeanUtils.copyProperties(medicalRecord, persistedMedicalRecord, "id", "person");
        return medicalRecordRepository.save(persistedMedicalRecord);
    }

    @Override
    public MedicalRecord findMedicalRecordByFirstAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findMedicalRecordByFirstAndLastName(firstName, lastName);
    }


    @Override
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.deleteMedicalRecord(medicalRecord);

    }


}
