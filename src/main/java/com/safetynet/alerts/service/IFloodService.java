package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;
import com.safetynet.alerts.dto.PersonMedicalRecordDTO;

import java.util.List;
import java.util.Map;

public interface IFloodService {

    Map<String,List<PersonMedicalRecordDTO>> findAddressPersonsMedicalRecords(List<Integer> stations);
}
