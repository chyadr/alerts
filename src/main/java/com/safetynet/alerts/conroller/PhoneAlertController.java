package com.safetynet.alerts.conroller;

import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.service.IPhoneAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("phoneAlert")
public class PhoneAlertController {
    private static final Logger log = LogManager.getLogger(PersonMapper.class);
    private final IPhoneAlertService phoneAlertService;

    public PhoneAlertController(IPhoneAlertService phoneAlertService) {
        this.phoneAlertService = phoneAlertService;
    }

    @GetMapping
    public ResponseEntity<List<String>> findPhoneNumberByFireStation(@RequestParam(name = "firestation") Integer fireStationNumber) {
        log.info("[phoneAlert] - params [{}]", fireStationNumber);

        List<String> phoneNumber = phoneAlertService.findPhoneNumberByFireStation(fireStationNumber);
        log.info("[phoneAlert] - Response {}", phoneNumber.toString());

        return ResponseEntity.status(HttpStatus.OK).body(phoneNumber);
    }
}
