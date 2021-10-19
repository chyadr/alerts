package com.safetynet.alerts.conroller;


import com.safetynet.alerts.service.ICommunityEmailService;
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
@RequestMapping("communityEmail")
public class CommunityEmailController {

    private static final Logger log = LogManager.getLogger(CommunityEmailController.class);
    private final ICommunityEmailService communityEmailService;

    public CommunityEmailController(ICommunityEmailService communityEmailService) {
        this.communityEmailService = communityEmailService;
    }

    @GetMapping
    public ResponseEntity<List<String>> findAllAddressMailsByCity(@RequestParam(name = "city") String city) {
        log.info("[communityEmail] - params [{}]", city);
        List<String> addressMails = communityEmailService.findAllAddressMailsByCity(city);

        log.info("[communityEmail] - Response {}", addressMails.toString());

        return ResponseEntity.status(HttpStatus.OK).body(addressMails);
    }

}
