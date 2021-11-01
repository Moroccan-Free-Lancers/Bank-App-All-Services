package app.ws.bank_services_user.services;

import app.ws.bank_services_user.shared.dto.CustomerDto;

public interface CustomerCounselorService {
    CustomerDto createCustomerWithCounselor(CustomerDto customerDto, String counselorId);
}
