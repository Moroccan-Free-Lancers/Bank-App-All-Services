package app.ws.bank_services_user.services.imlp;

import app.ws.bank_services_user.entities.BeneficiaryEntity;
import app.ws.bank_services_user.entities.CustomerEntity;
import app.ws.bank_services_user.exceptions.BeneficiaryException;
import app.ws.bank_services_user.exceptions.CustomerException;
import app.ws.bank_services_user.repositories.CustomerRepository;
import app.ws.bank_services_user.services.BeneficiaryService;
import app.ws.bank_services_user.services.CustomersBeneficiaryService;
import app.ws.bank_services_user.shared.Utils;
import app.ws.bank_services_user.shared.dto.BeneficiaryDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomersBeneficiaryImpl implements CustomersBeneficiaryService {

    private final CustomerRepository customerRepository;
    private final BeneficiaryService beneficiaryService;
    private final Utils util;

    @Autowired
    public CustomersBeneficiaryImpl(CustomerRepository customerRepository, BeneficiaryService beneficiaryService, Utils util) {
        this.customerRepository = customerRepository;
        this.beneficiaryService = beneficiaryService;
        this.util = util;
    }


    @Override
    public CustomerDto addBeneficiaryToCustomer(String idCustomer, BeneficiaryDto beneficiaryDto) {

        CustomerEntity customerChecker = customerRepository.findByCustomerId(idCustomer).orElseThrow(
                () -> new CustomerException("Customer doesn't exist to affect them beneficiary")
        );

        ModelMapper modelMapper = new ModelMapper();
        BeneficiaryEntity beneficiaryToCheck = modelMapper.map(beneficiaryDto, BeneficiaryEntity.class);
        List<BeneficiaryEntity> beneficiariesCustomer = new ArrayList<>(customerChecker.getBeneficiaries());
        if (beneficiariesCustomer.contains(beneficiaryToCheck)) {
            throw new BeneficiaryException("this beneficiary already exist for the customer");
        }

        beneficiaryToCheck.setBeneficiaryId(util.generatedStringId(30));
        beneficiaryToCheck.setCustomer(customerChecker);
        beneficiaryService.createBeneficiary(modelMapper.map(beneficiaryToCheck, BeneficiaryDto.class));
        beneficiariesCustomer.add(beneficiaryToCheck);
        customerChecker.setBeneficiaries(beneficiariesCustomer);

        return modelMapper.map(customerChecker, CustomerDto.class);
    }

    @Override
    public CustomerDto updateBeneficiary(String idCustomer, String idBeneficiary, BeneficiaryDto beneficiaryDto) {

        ModelMapper modelMapper = new ModelMapper();

        CustomerEntity customerChecker = customerRepository.findByCustomerId(idCustomer).orElseThrow(
                () -> new CustomerException("Customer doesn't exist to update beneficiary")
        );

        BeneficiaryDto beneficiaryUpdated = beneficiaryService.updateBeneficiary(
                idBeneficiary, beneficiaryDto
        );

        if (beneficiaryUpdated == null) throw new BeneficiaryException("Error occurred in update method for beneficiary");

        BeneficiaryEntity beneficiaryEntityUpdated = modelMapper.map(beneficiaryUpdated, BeneficiaryEntity.class);

        List<BeneficiaryEntity> beneficiariesCustomer = new ArrayList<>(customerChecker.getBeneficiaries());

        beneficiariesCustomer.removeIf(beneficiary -> beneficiary.getBeneficiaryId().equals(idBeneficiary));

        beneficiariesCustomer.add(beneficiaryEntityUpdated);

        customerChecker.setBeneficiaries(beneficiariesCustomer);

        CustomerEntity customerUpdatedBeneficiaryEntity = customerRepository.save(customerChecker);

        return modelMapper.map(customerUpdatedBeneficiaryEntity, CustomerDto.class);
    }

    @Override
    public void deleteBeneficiaryFromCustomer(String idCustomer, String idBeneficiary) {

        customerRepository.findByCustomerId(idCustomer).orElseThrow(
                () -> new CustomerException("Customer doesn't exist to delete beneficiary")
        );

        BeneficiaryDto beneficiaryChecker = beneficiaryService.getBeneficiaryById(idBeneficiary);
        if (beneficiaryChecker == null)
            throw new BeneficiaryException("Beneficiary doesn't exist !!");
        if (!beneficiaryChecker.getCustomer().getCustomerId().equals(idCustomer))
            throw new CustomerException("The customer cannot delete this beneficiary !! ");

        try {
            beneficiaryService.deleteBeneficiary(beneficiaryChecker);
        } catch (BeneficiaryException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<BeneficiaryDto> getBeneficiaries(String idCustomer) {

        CustomerEntity customerChecker = customerRepository.findByCustomerId(idCustomer).orElseThrow(
                () -> new CustomerException("Customer doesn't exist to retrieve beneficiaries")
        );

        ModelMapper modelMapper = new ModelMapper();

        List<BeneficiaryDto> beneficiariesDto = new ArrayList<>();

        List<BeneficiaryEntity> beneficiariesEntity = new ArrayList<>(customerChecker.getBeneficiaries());

        beneficiariesEntity.forEach(beneficiary -> beneficiariesDto.add(modelMapper.map(beneficiary, BeneficiaryDto.class)));


        return beneficiariesDto;
    }


}
