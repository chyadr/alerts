package com.safetynet.alerts.conroller;
import com.safetynet.alerts.dto.ChildrenAdultsInfoDTO;
import com.safetynet.alerts.service.IChildAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("childAlert")
public class ChildAlertController {

    private static final Logger log = LogManager.getLogger(ChildAlertController.class);

    private final IChildAlertService childAlertService;

    public ChildAlertController(IChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping
    public ResponseEntity<Object> findPersonsByAddress(@RequestParam(name = "address") String address) {
        log.info("[childAlert] - params [{}]", address);

        ChildrenAdultsInfoDTO childrenAdultsInfoDTO = childAlertService.findChildrenAdults(address);

        if (CollectionUtils.isEmpty(childrenAdultsInfoDTO.getChildren())) {
            log.warn("[childAlert] - No Children found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Children found");
        }

        log.info("[childAlert] - Response {}", childrenAdultsInfoDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(childrenAdultsInfoDTO);
    }


}