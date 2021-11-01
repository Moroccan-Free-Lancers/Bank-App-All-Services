package app.ws.bank_services_user.services;

import app.ws.bank_services_user.shared.dto.BeneficiaryDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import java.util.List;

public interface CustomersBeneficiaryService {

    CustomerDto addBeneficiaryToCustomer(String idCustomer, BeneficiaryDto beneficiaryDto);
    CustomerDto updateBeneficiary(String idCustomer, String idBeneficiary,BeneficiaryDto beneficiaryDto);
    void deleteBeneficiaryFromCustomer(String idCustomer, String idBeneficiary);
    List<BeneficiaryDto> getBeneficiaries(String idCustomer);
}
