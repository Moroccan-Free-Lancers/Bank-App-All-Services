package com.apps.ws.controllers;

import com.apps.ws.entities.CustomerResponse;
import com.apps.ws.exceptions.RestTemplateException;
import com.apps.ws.requests.CheckRequest;
import com.apps.ws.responses.CheckResponse;
import com.apps.ws.services.CheckService;
import com.apps.ws.shared.dto.CheckDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    CheckService checkService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(path = "/{checkId}")
    public CheckResponse getCheque(@PathVariable String checkId) {

        CheckDto checkDto = checkService.getCheck(checkId);
        CheckResponse cheque = new CheckResponse();
        BeanUtils.copyProperties(checkDto, cheque);

        return cheque;
    }

    @GetMapping(path = "/list/{userId}")
    public List<CheckResponse> getAllCheque(@PathVariable String userId) {

        // The user checher using restTemplate with Bearer Token

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmRlcnJhaG1hbkBnbWFpbC5jb20iLCJleHAiOjE2MzY0OTAxMjZ9.zeF3zjaor9li8H1m3xMXBd7iwqlFjzocBt9QyDWAbf56RUPctsxQnrY3XEqjGGwBzei7vp7rmW6QE0wX8i7nQA"); //accessToken can be the secret key you generate.
        HttpEntity request = new HttpEntity(headers);
        try {
            ResponseEntity<CustomerResponse> customerResponse = restTemplate.exchange("http://localhost:8085/bank-service-user/customers/"
                    + userId, HttpMethod.GET, request, CustomerResponse.class);
        } catch (RestClientException e) {
            throw new RestTemplateException("User not found !");
        }

        List<CheckDto> checkDtos = checkService.getAllCheckByUser(userId);
        List<CheckResponse> checkRespons = new ArrayList<>();
        checkDtos.forEach(cheque -> {

            CheckResponse checkResponse = new CheckResponse();
            BeanUtils.copyProperties(cheque, checkResponse);
            checkRespons.add(checkResponse);

        });

        return checkRespons;
    }


    @PostMapping(path = "/{userId}")
    public CheckResponse postCheque(@PathVariable String userId, @Valid @RequestBody CheckRequest checkRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmRlcnJhaG1hbkBnbWFpbC5jb20iLCJleHAiOjE2MzY1NDk1OTN9.HMq_Pq50rmMCcgmwS6eQER5_gC4EBoZ90buDLL74Vn70d6CHtc9cnEQ9ypzvm6ILtvgxS7bPp7kWB3RW7NYaBg"); //accessToken can be the secret key you generate.
        HttpEntity request = new HttpEntity(headers);

        try {
            ResponseEntity<CustomerResponse> customerResponse = restTemplate.exchange("http://localhost:8085/bank-service-user/customers/"
                    + userId, HttpMethod.GET, request, CustomerResponse.class);

        } catch (RestClientException e) {
            throw new RestTemplateException("User not found !");
        }

        CheckDto cheque = new CheckDto();
        BeanUtils.copyProperties(checkRequest, cheque);
        cheque.setUserId(userId);
        CheckDto chequeCreated = checkService.postCheck(cheque);
        CheckResponse checkResponse = new CheckResponse();
        BeanUtils.copyProperties(chequeCreated, checkResponse);

        return checkResponse;
    }


    @DeleteMapping(path = "/{checkId}")
    public String deleteCheque(@PathVariable String checkId) {

        String string = checkService.removeCheck(checkId);

        return string;
    }
}