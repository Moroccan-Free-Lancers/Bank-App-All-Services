package app.ws.bank_services_user.services;


import app.ws.bank_services_user.shared.dto.AgencyDto;

public interface AgencyService {
    AgencyDto createAgency(AgencyDto agencyDto);

    AgencyDto printAgency(String id);

    String removeAgency(String id);
}
