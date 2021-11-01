package app.ws.bank_services_user.controllers;

import app.ws.bank_services_user.requests.CounselorRequest;
import app.ws.bank_services_user.responses.CounselorResponse;
import app.ws.bank_services_user.services.CounselorService;
import app.ws.bank_services_user.shared.dto.CounselorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counselors")
public class CounselorController {


    private final CounselorService counselorService;

    @Autowired
    public CounselorController(CounselorService counselorService) {
        this.counselorService = counselorService;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CounselorResponse createCounselor(@RequestBody CounselorRequest counselorRequest){

        ModelMapper modelMapper = new ModelMapper();

        CounselorDto counselorDto = modelMapper.map(counselorRequest, CounselorDto.class);

        CounselorDto createdCounselorDto = counselorService.createCounselor(counselorDto);

        CounselorResponse counselorResponse = modelMapper.map(createdCounselorDto, CounselorResponse.class);

        return counselorResponse;
    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public CounselorResponse updateCounselor(@RequestBody CounselorRequest counselorRequest, @PathVariable String id){
        ModelMapper modelMapper = new ModelMapper();

        CounselorDto counselorDto = modelMapper.map(counselorRequest, CounselorDto.class);

        CounselorDto updatedCounselorDto = counselorService.updateCounselor(counselorDto, id);

        CounselorResponse counselorResponse = modelMapper.map(updatedCounselorDto, CounselorResponse.class);

        return counselorResponse;
    }
}

