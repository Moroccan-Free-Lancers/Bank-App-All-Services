package ma.master.compte.Controller;

import ma.master.compte.Dto.AccountDto;
import ma.master.compte.Entity.CustomerResponse;
import ma.master.compte.Exceptions.RestTemplateException;
import ma.master.compte.Requests.AccountRequest;
import ma.master.compte.Response.AccountResponse;
import ma.master.compte.Response.DocumentsResponse;
import ma.master.compte.Response.OperationResponse;
import ma.master.compte.Service.Impl.AccountServiceImpl;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountServiceImpl accountServiceImpl;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/get/{accountId}")
    public AccountResponse getComptes(@PathVariable("accountId") String accountId) {

        AccountDto accountDto = accountServiceImpl.getCompteById(accountId);
        AccountResponse compteResponse = new AccountResponse();
        BeanUtils.copyProperties(accountDto, compteResponse);

        return compteResponse;
    }

    @PutMapping("/put/{accountId}")
    public void getComptes(@PathVariable("accountId") String accountId, @RequestParam double newBalance) {

        accountServiceImpl.putBalanceAccountById(accountId, newBalance);

        return ;
    }

    @PostMapping(path = "/post/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountResponse postCard(@PathVariable String userId, @Valid @RequestBody AccountRequest accountRequest) {

        // The user checher usin restTemplate with Bearer Token

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmRlcnJhaG1hbkBnbWFpbC5jb20iLCJleHAiOjE2MzY0OTAxMjZ9.zeF3zjaor9li8H1m3xMXBd7iwqlFjzocBt9QyDWAbf56RUPctsxQnrY3XEqjGGwBzei7vp7rmW6QE0wX8i7nQA"); //accessToken can be the secret key you generate.
        HttpEntity request = new HttpEntity(headers);
        try {
            ResponseEntity<CustomerResponse> customerResponse = restTemplate.exchange("http://localhost:8085/bank-service-user/customers/"
                    + userId, HttpMethod.GET, request, CustomerResponse.class);
        } catch (RestClientException e) {
            throw new RestTemplateException("User not found !");
        }

        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(accountRequest, accountDto);
        accountDto.setUserId(userId);
        AccountDto accountDtoCreated = accountServiceImpl.postAccount(accountDto);
        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(accountDtoCreated, accountResponse);

        return accountResponse;
    }

    @GetMapping(value = "/getall/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountResponse> getAllCompteByUserId(@PathVariable("userId") String userId) {

        // The user checher usin restTemplate with Bearer Token

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmRlcnJhaG1hbkBnbWFpbC5jb20iLCJleHAiOjE2MzY0OTAxMjZ9.zeF3zjaor9li8H1m3xMXBd7iwqlFjzocBt9QyDWAbf56RUPctsxQnrY3XEqjGGwBzei7vp7rmW6QE0wX8i7nQA"); //accessToken can be the secret key you generate.
        HttpEntity request = new HttpEntity(headers);
        try {
            ResponseEntity<CustomerResponse> customerResponse = restTemplate.exchange("http://localhost:8085/bank-service-user/customers/"
                    + userId, HttpMethod.GET, request, CustomerResponse.class);
        } catch (RestClientException e) {
            throw new RestTemplateException("User not found !");
        }

        ModelMapper modelMapper = new ModelMapper();
        List<AccountResponse> comptesResponse = new ArrayList<>();
        accountServiceImpl.getAllCompteByUserId(userId).forEach(compte -> comptesResponse.add(modelMapper.map(compte, AccountResponse.class)));

        return comptesResponse;

    }


    @GetMapping(path = "/operations/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OperationResponse> getOperationsByCompteId(@PathVariable("accountId") String accountId) {

        ModelMapper modelMapper = new ModelMapper();
        List<OperationResponse> operationsResonse = new ArrayList<>();
        accountServiceImpl.getOperationsByCompteId(accountId).forEach(operationDto ->
                operationsResonse.add(modelMapper.map(operationDto, OperationResponse.class)));
        return operationsResonse;
    }

    @GetMapping(path = "/documents/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DocumentsResponse> getDocumentsByCompteId(@PathVariable("accountId") String accountId) {

        ModelMapper modelMapper = new ModelMapper();
        List<DocumentsResponse> documentsResponse = new ArrayList<>();
        accountServiceImpl.getDocumentsByCompteId(accountId).forEach(documentDto ->
                documentsResponse.add(modelMapper.map(documentDto, DocumentsResponse.class)));
        return documentsResponse;
    }

}
