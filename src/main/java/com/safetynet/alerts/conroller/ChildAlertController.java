package com.safetynet.alerts.conroller;

import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.dto.PersonDTO;
import com.safetynet.alerts.mapper.PersonMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("childAlert")
public class ChildAlertController {

    private static final Logger log = LogManager.getLogger(ChildAlertController.class);

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Object> findPersonsByAddress(@RequestParam(name = "address") String address) {
        log.info("[childAlert] - params [{}]",address);
        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = new ChildrenAdultsInfoDTO();
        List<Person> persons = personService.findPersonsByAddress(address);
        List<PersonDTO> personDTOS = persons.stream().map(PersonMapper::mapPerson).collect(Collectors.toList());

        childrenAdultsInfoDTO.setChildren(personDTOS.stream().filter(p -> p.getAge()<18).collect(Collectors.toList()));
        childrenAdultsInfoDTO.setAdults(personDTOS.stream().filter(p -> p.getAge() >=18) .collect(Collectors.toList()));

        if (CollectionUtils.isEmpty(childrenAdultsInfoDTO.getChildren())){
            log.warn("[childAlert] - No Children found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Children found");
        }

        log.info("[childAlert] - Response {}",childrenAdultsInfoDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(childrenAdultsInfoDTO);
    }


}