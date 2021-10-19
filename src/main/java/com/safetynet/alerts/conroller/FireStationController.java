package com.safetynet.alerts.conroller;

import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("firestation")
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);
    private final IPersonService personService;
    private final IFireStationService fireStationService;

    public FireStationController(IPersonService personService, IFireStationService fireStationService) {
        this.personService = personService;
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public ResponseEntity<PersonInfosDTO> findPersonsByStationNumber(@RequestParam(name = "stationNumber") Integer stationNumber) {
        log.info("[firestation] - params [{}]", stationNumber);

        PersonInfosDTO personsInfosDTO = fireStationService.findPersonsByStationNumber(stationNumber);
        log.info("[firestation] - Response {}", personsInfosDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(personsInfosDTO);
    }

    @PostMapping()
    public ResponseEntity<Object> createFireStation(@RequestBody FireStation fireStation) {
        log.info("[firestation][createFireStation] - params [{}]", fireStation.toString());

        if (fireStation.getAddress() == null || fireStation.getAddress().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given is required !");
        }
        List<FireStation> fireStations = fireStationService.findFireStationsByAddressId(fireStation.getAddress().getId());

        if (!CollectionUtils.isEmpty(fireStations)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given is already mapped to an existing FireStation !");
        }

        FireStation persistedFireStation = fireStationService.createFireStation(fireStation);
        log.info("[firestation][createFireStation] - Response {}", persistedFireStation.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(persistedFireStation);
    }

    @PutMapping
    public ResponseEntity<Object> updateFireStationNumber(@RequestBody FireStation fireStation) {

        log.info("[firestation][updateFireStationNumber] - params [{}]", fireStation.toString());

        Optional<FireStation> persistedFireStation = fireStationService.findFireStationById(fireStation.getId());

        if (persistedFireStation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FireStation given is not found !");
        }

        if (!persistedFireStation.get().getAddress().getId().equals(fireStation.getAddress().getId())) {
            List<FireStation> fireStations = fireStationService.findFireStationsByAddressId(fireStation.getAddress().getId());

            if (!CollectionUtils.isEmpty(fireStations)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given is already mapped to an existing FireStation !");
            }
        }


        BeanUtils.copyProperties(fireStation, persistedFireStation.get(), "id");


        FireStation updatedFireStation = fireStationService.saveFireStation(persistedFireStation.get());
        log.info("[firestation] [updateFireStationNumber] - Response {}", updatedFireStation.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedFireStation);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFireStationById(@RequestParam Integer id) {
        log.info("[firestation][deleteFireStationById] - params [{}]", id);

        Optional<FireStation> fireStation = fireStationService.findFireStationById(id);
        if (fireStation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No FireStation To Be Deleted");
        }

        fireStationService.deleteFireStation(fireStation.get());
        log.info("[firestation][deleteFireStationById] - Response {}", fireStation.get().toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FireStation Successfully Deleted");
    }

}
