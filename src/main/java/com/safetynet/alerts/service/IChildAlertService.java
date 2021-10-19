package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;

public interface IChildAlertService {

    ChildrenAdultsInfoDTO findChildrenAdults(String address);

}
