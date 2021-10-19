package com.safetynet.alerts.conroller;


import com.safetynet.alerts.dto.PersonFireStationNumberDTO;
import com.safetynet.alerts.service.IFireService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fire")
public class FireController {
    private static final Logger log = LogManager.getLogger(FireController.class);
    private final IFireService fireService;

    public FireController(IFireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping
    public ResponseEntity<PersonFireStationNumberDTO> findPersonsByStationNumber(@RequestParam(name = "address") String address) {
        log.info("[fire] - params [{}]", address);

        PersonFireStationNumberDTO personFireStationNumberDTO = fireService.findPersonFireStationNumber(address);

        log.info("[fire] - Response {}", personFireStationNumberDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(personFireStationNumberDTO);
    }
}