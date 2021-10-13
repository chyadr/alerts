package com.safetynet.alerts.conroller;


import com.safetynet.alerts.service.impl.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonService personService;
    @GetMapping
    public ResponseEntity<List<String>> findAllAddressMailsByCity(@RequestParam(name = "city") String city) {
        log.info("[communityEmail] - params [{}]",city);
        List<String> addressMails = personService.findAllAddressMailsByCity(city);

        log.info("[communityEmail] - Response {}",addressMails.toString());

        return ResponseEntity.status(HttpStatus.OK).body(addressMails);
    }

}
