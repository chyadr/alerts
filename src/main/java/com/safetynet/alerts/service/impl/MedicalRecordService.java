package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.service.IMedicalRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService implements IMedicalRecordService {


    private final Data data;

    public MedicalRecordService(Data data) {
        this.data = data;
    }

    @Override
    public com.safetynet.alerts.model.MedicalRecord createMedicalRecord(com.safetynet.alerts.model.MedicalRecord medicalRecord) {
        data.getMedicalrecords().add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public com.safetynet.alerts.model.MedicalRecord updateMedicalRecord(com.safetynet.alerts.model.MedicalRecord medicalRecord) {

        data.getMedicalrecords().forEach(m -> {
            if (m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName())){
                BeanUtils.copyProperties(medicalRecord, m,"firstName","lastName");
            }
        });

        return medicalRecord;
    }


    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        data.getMedicalrecords().removeIf(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName));

    }

    @Override
    public boolean existMedicalRecord(String firstName, String lastName) {
        return data.getMedicalrecords().stream().anyMatch(m -> m.getLastName().equals(lastName) && m.getFirstName().equals(firstName));
    }


}
