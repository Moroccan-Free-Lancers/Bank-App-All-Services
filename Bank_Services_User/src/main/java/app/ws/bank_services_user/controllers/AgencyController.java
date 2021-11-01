package app.ws.bank_services_user.controllers;


import app.ws.bank_services_user.requests.AgencyRequest;
import app.ws.bank_services_user.responses.AgencyResponse;
import app.ws.bank_services_user.services.AgencyService;
import app.ws.bank_services_user.shared.dto.AgencyDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agencies")
public class AgencyController {
    @Autowired
    AgencyService agencyService;

    @GetMapping(path = "/{id}")
    public AgencyResponse getAgency(@PathVariable String id) {

        AgencyDto agency = agencyService.printAgency(id);

        AgencyResponse agencyResponse = new AgencyResponse();
        BeanUtils.copyProperties(agency, agencyResponse);

        return agencyResponse;
    }

    @PostMapping
    public AgencyResponse postAgency(@Valid @RequestBody AgencyRequest agencyRequest) {
        AgencyDto agencyDto = new AgencyDto();

        BeanUtils.copyProperties(agencyRequest, agencyDto);

        AgencyDto createdAgency = agencyService.createAgency(agencyDto);

        AgencyResponse agencyResponse = new AgencyResponse();

        BeanUtils.copyProperties(createdAgency, agencyResponse);
        return agencyResponse;
    }

    @PutMapping
    public String putAgency() {
        return "put Agence";
    }

    @DeleteMapping(path = "/{id}")
    public String deleteAgency(@PathVariable String id) {

        String response = agencyService.removeAgency(id);

        return response;
    }
}
