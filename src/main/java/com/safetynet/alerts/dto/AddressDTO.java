package com.safetynet.alerts.dto;


import java.util.Comparator;
import java.util.Objects;

public class AddressDTO implements Comparable<AddressDTO>{

    private String address;
    private int zip;
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    @Override
    public String toString() {
        return "AddressDTO{" +
                "address='" + address + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public int compareTo(AddressDTO o) {
        if(o == null){
            return -1;
        }
        final Comparator<AddressDTO> comparator = Comparator.comparing(AddressDTO::getAddress).thenComparing(AddressDTO::getCity).thenComparing(AddressDTO::getZip);
        return comparator.compare(this,o);
    }
}
