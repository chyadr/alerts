package com.safetynet.alerts.mapper;


import com.safetynet.alerts.dto.MedicalRecordDTO;
import com.safetynet.alerts.model.Allergy;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Medication;

import java.util.stream.Collectors;

public class MedicalRecordMapper {


    public static MedicalRecordDTO mapMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecordDTO medicalRecordsDTO = new MedicalRecordDTO();
        medicalRecordsDTO.setMedications(medicalRecord.getMedications().stream().map(Medication::getName).collect(Collectors.toList()));
        medicalRecordsDTO.setAllergies(medicalRecord.getAllergies().stream().map(Allergy::getName).collect(Collectors.toList()));
        return medicalRecordsDTO;
    }
}
