package app.ws.bank_services_user.services;

import app.ws.bank_services_user.shared.dto.BeneficiaryDto;


public interface BeneficiaryService {

     BeneficiaryDto updateBeneficiary(String idBeneficiary, BeneficiaryDto beneficiaryDto);
     BeneficiaryDto createBeneficiary(BeneficiaryDto beneficiaryDto);
     void deleteBeneficiary(BeneficiaryDto beneficiaryDto);
     BeneficiaryDto getBeneficiaryById(String id);
}
