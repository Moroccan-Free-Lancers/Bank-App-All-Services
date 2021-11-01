package app.ws.bank_services_user.controllers;

import app.ws.bank_services_user.requests.BeneficiaryRequest;
import app.ws.bank_services_user.responses.BeneficiaryResponse;
import app.ws.bank_services_user.responses.CustomerResponse;
import app.ws.bank_services_user.services.CustomersBeneficiaryService;
import app.ws.bank_services_user.shared.dto.BeneficiaryDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer&beneficiary")
public class CustomerBeneficiaryController {

    private final CustomersBeneficiaryService customersBeneficiaryService;

    @Autowired
    public CustomerBeneficiaryController(CustomersBeneficiaryService customersBeneficiaryService) {
        this.customersBeneficiaryService = customersBeneficiaryService;
    }

    @GetMapping(
            value = "/getbeneficiaries/{idCustomer}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<BeneficiaryResponse> getBeneficiariesFromCustomer(@PathVariable String idCustomer) {
        ModelMapper modelMapper = new ModelMapper();

        List<BeneficiaryResponse> beneficiaryResponses = new ArrayList<>();

        customersBeneficiaryService.getBeneficiaries(idCustomer).forEach(beneficiary -> beneficiaryResponses.add(modelMapper.map(beneficiary, BeneficiaryResponse.class)));

        return beneficiaryResponses;
    }

    @PostMapping(
            value = "/addbeneficiary/{idCustomer}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse addBeneficiaryToCustomer(
            @PathVariable String idCustomer,
            @RequestBody BeneficiaryRequest beneficiaryRequest
    ) {

        ModelMapper modelMapper = new ModelMapper();

        BeneficiaryDto beneficiaryDto = modelMapper.map(beneficiaryRequest, BeneficiaryDto.class);

        CustomerDto addedBeneficiaryToCustomerDto = customersBeneficiaryService.addBeneficiaryToCustomer(idCustomer, beneficiaryDto);

        CustomerResponse customerResponse = modelMapper.map(addedBeneficiaryToCustomerDto, CustomerResponse.class);

        return customerResponse;
    }

    @PutMapping(
            value = "/updatebeneficiary/{idCustomer}/to/{idBeneficiary}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CustomerResponse updateBeneficiaryToCustomer(
            @PathVariable String idCustomer,
            @PathVariable String idBeneficiary,
            @RequestBody BeneficiaryRequest beneficiaryRequest
    ) {

        ModelMapper modelMapper = new ModelMapper();

        BeneficiaryDto beneficiaryDto = modelMapper.map(beneficiaryRequest, BeneficiaryDto.class);

        CustomerDto addedBeneficiaryToCustomerDto = customersBeneficiaryService.updateBeneficiary(idCustomer, idBeneficiary, beneficiaryDto);

        CustomerResponse customerResponse = modelMapper.map(addedBeneficiaryToCustomerDto, CustomerResponse.class);

        return customerResponse;
    }

    @DeleteMapping(
            value = "/deletebeneficiary/{idCustomer}/to/{idBeneficiary}"
    )
    public String deleteBeneficiaryFromCustomer(@PathVariable String idCustomer, @PathVariable String idBeneficiary) {

        customersBeneficiaryService.deleteBeneficiaryFromCustomer(idCustomer, idBeneficiary);
        return "Beneficiary was successfully deleted";
    }
}
