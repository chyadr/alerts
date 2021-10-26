package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.FireStation;

import java.util.List;
import java.util.Optional;

public interface IFireStationService {

    FireStation createFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(String address);

    boolean existFireStationsByAddress(String address);

    PersonInfosDTO findPersonsByStationNumber(Integer stationNumber);
}

