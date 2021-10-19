package com.safetynet.alerts.service;

import java.util.List;

public interface ICommunityEmailService {

    List<String> findAllAddressMailsByCity(String city);
}
