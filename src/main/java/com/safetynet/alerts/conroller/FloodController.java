package com.safetynet.alerts.conroller;


import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;
import com.safetynet.alerts.dto.PersonMedicalRecordDTO;
import com.safetynet.alerts.service.IFloodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("flood")
public class FloodController {
    private static final Logger log = LogManager.getLogger(FloodController.class);
    private final IFloodService floodService;

    public FloodController(IFloodService floodService) {
        this.floodService = floodService;
    }

    @GetMapping("stations")
    public ResponseEntity<Map<String,List<PersonMedicalRecordDTO>>> findAddressPersonsMedicalRecordsByStations(@RequestParam(name = "stations") List<Integer> stations) {
        log.info("[flood] - params [{}]", stations);

        Map<String,List<PersonMedicalRecordDTO>> map = floodService.findAddressPersonsMedicalRecords(stations);

        log.info("[flood] - Response {}", map.toString());

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }


}



