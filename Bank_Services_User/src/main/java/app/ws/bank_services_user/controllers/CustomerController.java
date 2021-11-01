package app.ws.bank_services_user.controllers;

import app.ws.bank_services_user.requests.CustomerRequest;
import app.ws.bank_services_user.responses.CustomerResponse;
import app.ws.bank_services_user.services.CustomerService;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController//http://localhost:8082/customers
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse createCustomer(
          @Valid @RequestBody CustomerRequest customerRequest
    ) {

        ModelMapper modelMapper = new ModelMapper();

        CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);

        CustomerDto createdCustomerDto = customerService.createCustomer(customerDto);

        CustomerResponse customerResponse = modelMapper.map(createdCustomerDto, CustomerResponse.class);

        return customerResponse;
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse getCustomerById(@PathVariable String id) {

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(customerService.getCustomerById(id), CustomerResponse.class);
    }

}
