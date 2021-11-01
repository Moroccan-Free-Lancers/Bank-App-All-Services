package bank.app.card_service.controllers;


import bank.app.card_service.requests.CeilingRequest;
import bank.app.card_service.responses.CeilingResponse;
import bank.app.card_service.services.impl.CeilingServiceImpl;
import bank.app.card_service.shared.dto.CeilingDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/ceiling")
public class CeilingController {

    @Autowired
    CeilingServiceImpl ceilingServiceImpl;

    @GetMapping(path="/get/{id}")
    public CeilingResponse getCeiling(@PathVariable String id){

        CeilingDto ceilingDto = ceilingServiceImpl.getCeiling(id);
        CeilingResponse ceilingResponse = new CeilingResponse();
        BeanUtils.copyProperties(ceilingDto,ceilingResponse);

        return ceilingResponse;
    }

    @PostMapping(path = "/post",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CeilingResponse postCard(@Valid @RequestBody CeilingRequest ceilingRequest) {

        CeilingDto ceilingDto = new CeilingDto();
        BeanUtils.copyProperties(ceilingRequest, ceilingDto);
        CeilingDto ceilingCreated = ceilingServiceImpl.postCeiling(ceilingDto);
        CeilingResponse ceilingResponse = new CeilingResponse();
        BeanUtils.copyProperties(ceilingCreated, ceilingResponse);

        return ceilingResponse;
    }

    @GetMapping(
            value = "/getall",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CeilingResponse> getCeilings() {

        ModelMapper modelMapper = new ModelMapper();
        List<CeilingResponse> ceilingsResponse = new ArrayList<>();
        ceilingServiceImpl.getCeilings().forEach(ceiling -> ceilingsResponse.add(modelMapper.map(ceiling, CeilingResponse.class)));

        return ceilingsResponse;
    }

}

