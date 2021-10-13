package com.safetynet.alerts.conroller;


import com.safetynet.alerts.dto.AddressDTO;
import com.safetynet.alerts.dto.AddressPersonsMedicalRecordDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;


@RestController
@RequestMapping("flood")
public class FloodController {
    private static final Logger log = LogManager.getLogger(FloodController.class);

    @Autowired
     private PersonService personService;

    @GetMapping("stations")
    public ResponseEntity<List<AddressPersonsMedicalRecordDTO>> findAddressPersonsMedicalRecordsByStations(@RequestParam(name = "stations") List<Integer> stations) {
        log.info("[flood] - params [{}]",stations);
        List<Person> persons = personService.findPersonsByStations(stations);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());
        Map<AddressDTO, Set<PersonDTO>> map = personDTOS.stream().collect(Collectors.groupingBy(PersonDTO::getAddressDTO,TreeMap::new, Collectors.toSet()));

        List<AddressPersonsMedicalRecordDTO> addressPersonsMedicalRecordDTOS = map.entrySet().stream().map(m->  {
            AddressPersonsMedicalRecordDTO adr = new AddressPersonsMedicalRecordDTO();
            adr.setPersonDTOS(m.getValue());
            adr.setAddressDTO(m.getKey());
            return adr;
        }).collect(Collectors.toList());
        log.info("[flood] - Response {}", addressPersonsMedicalRecordDTOS.toString());

        return ResponseEntity.status(HttpStatus.OK).body(addressPersonsMedicalRecordDTOS);
    }


}



