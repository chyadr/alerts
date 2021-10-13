package com.safetynet.alerts.mapper;

import com.safetynet.alerts.dto.AddressDTO;
import com.safetynet.alerts.model.Address;

public class AddressMapper {

    public static AddressDTO mapAddress(Address address){
        AddressDTO addressDTO= new AddressDTO();
        addressDTO.setAddress(address.getAddress());
        addressDTO.setCity(address.getCity());
        addressDTO.setZip(address.getZip());
        return addressDTO;

    }
}
