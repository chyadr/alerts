package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFireStationNumberDTO;


public interface IFireService {

    PersonFireStationNumberDTO findPersonFireStationNumber(String address);
}
