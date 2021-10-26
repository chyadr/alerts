package com.safetynet.alerts.conroller;

import com.safetynet.alerts.dto.PersonInfosDTO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.IFireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("firestation")
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);
    private final IFireStationService fireStationService;

    public FireStationController(IFireStationService fireStationService) {
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

        if (!StringUtils.hasLength(fireStation.getAddress()) || fireStation.getStation() == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given or Station is required !");
        }

        boolean existFireStation = fireStationService.existFireStationsByAddress(fireStation.getAddress());

        if (existFireStation) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given is already mapped to an existing FireStation !");
        }

        FireStation savedFireStation = fireStationService.createFireStation(fireStation);
        log.info("[firestation][createFireStation] - Response {}", savedFireStation.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedFireStation);
    }

    @PutMapping
    public ResponseEntity<Object> updateFireStationNumber(@RequestBody FireStation fireStation) {

        log.info("[firestation][updateFireStationNumber] - params [{}]", fireStation.toString());

        if (!StringUtils.hasLength(fireStation.getAddress()) || fireStation.getStation() == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address given or Station is required !");
        }

        boolean existFireStation = fireStationService.existFireStationsByAddress(fireStation.getAddress());

        if (!existFireStation) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FireStation given is not found !");
        }


        FireStation updatedFireStation = fireStationService.updateFireStation(fireStation);
        log.info("[firestation] [updateFireStationNumber] - Response {}", updatedFireStation.toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedFireStation);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFireStationByAddress(@RequestParam String address) {
        log.info("[firestation][deleteFireStationById] - params [{}]", address);

        boolean existFireStation = fireStationService.existFireStationsByAddress(address);

        if (!existFireStation) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No FireStation To Be Deleted");
        }

        fireStationService.deleteFireStation(address);
        log.info("[firestation][deleteFireStationById] - Response {}", address);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FireStation Successfully Deleted");
    }

}
