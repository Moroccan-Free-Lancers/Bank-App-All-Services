package app.ws.bank_services_user.services;

import app.ws.bank_services_user.shared.dto.BeneficiaryDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface CustomerService extends UserDetailsService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(String id);

}
