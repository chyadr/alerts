package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;

import java.util.List;

public interface IFloodService {

    List<AddressPersonsMedicalRecordDTO> findAddressPersonsMedicalRecords(List<Integer> stations);
}
