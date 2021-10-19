package com.safetynet.alerts.dto;

import java.util.Set;

public class AddressPersonsMedicalRecordDTO {


    private Set<PersonDTO> personDTOS;
    private AddressDTO addressDTO;


    public Set<PersonDTO> getPersonDTOS() {
        return personDTOS;
    }

    public void setPersonDTOS(Set<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
    }

    public AddressPersonsMedicalRecordDTO personDTOS(Set<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
        return this;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    @Override
    public String toString() {
        return "AddressPersonsMedicalRecordDTO{" +
                "personDTOS=" + personDTOS +
                ", addressDTO=" + addressDTO +
                '}';
    }
}

