package com.safetynet.alerts.dto;

import java.util.List;

public class AddressPersonsMedicalRecordDTO {


    private String address;
    private List<PersonMedicalRecordDTO> personMedicalRecordDTOS;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonMedicalRecordDTO> getPersonMedicalRecordDTOS() {
        return personMedicalRecordDTOS;
    }

    public void setPersonMedicalRecordDTOS(List<PersonMedicalRecordDTO> personMedicalRecordDTOS) {
        this.personMedicalRecordDTOS = personMedicalRecordDTOS;
    }

    @Override
    public String toString() {
        return "AddressPersonsMedicalRecordDTO{" +
                "address='" + address + '\'' +
                ", personMedicalRecordDTOS=" + personMedicalRecordDTOS +
                '}';
    }
}

