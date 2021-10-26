package com.safetynet.alerts.model;
public class FireStation {


    private Integer station;
    private String address;


    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public FireStation station(Integer station) {
        this.station = station;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FireStation address(String address) {
        this.address = address;
        return this;
    }


    @Override
    public String toString() {
        return "FireStation{" +
                "station=" + station +
                ", address='" + address + '\'' +
                '}';
    }
}
