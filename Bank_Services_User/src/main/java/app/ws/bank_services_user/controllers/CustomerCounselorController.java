package app.ws.bank_services_user.controllers;

import app.ws.bank_services_user.requests.CustomerRequest;
import app.ws.bank_services_user.responses.CustomerResponse;
import app.ws.bank_services_user.services.CustomerCounselorService;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer&counselor")
public class CustomerCounselorController {

    private final CustomerCounselorService customerCounselorService;

    @Autowired
    public CustomerCounselorController(CustomerCounselorService customerCounselorService) {
        this.customerCounselorService = customerCounselorService;
    }


    @PostMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse createCustomer(
           @Valid @RequestBody CustomerRequest customerRequest,
            @PathVariable String id
    ) {

        ModelMapper modelMapper = new ModelMapper();

        CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);

        CustomerDto createdCustomerDto = customerCounselorService.createCustomerWithCounselor(customerDto, id);

        CustomerResponse customerResponse = modelMapper.map(createdCustomerDto, CustomerResponse.class);

        return customerResponse;
    }
}
