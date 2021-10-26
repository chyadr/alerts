package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireDTO;


public interface IFireService {

    FireDTO findPersonFireStationNumber(String address);
}
