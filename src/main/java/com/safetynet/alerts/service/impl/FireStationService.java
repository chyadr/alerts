package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.model.Address;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.service.IFireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireStationService implements IFireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    @Override
    public FireStation createFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public FireStation saveFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public Optional<FireStation> findFireStationById(int id) {
        return  fireStationRepository.findById(id);
    }

    @Override
    public void deleteFireStation(FireStation fireStation) {
        fireStationRepository.delete(fireStation);
    }

    @Override
    public List<FireStation> findFireStationsByAddressId(int id) {
        return fireStationRepository.findFireStationsByAddressId(id);
    }


}





