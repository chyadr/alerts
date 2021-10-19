package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.FireStation;

import java.util.List;
import java.util.Optional;

public interface IFireStationService {

    FireStation createFireStation(FireStation fireStation);

    FireStation saveFireStation(FireStation fireStation);

    Optional<FireStation> findFireStationById(int id);

    void deleteFireStation(FireStation fireStation);

    List<FireStation> findFireStationsByAddressId(int id);

    PersonInfosDTO findPersonsByStationNumber(Integer stationNumber);
}

