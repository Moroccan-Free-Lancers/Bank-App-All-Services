package app.ws.bank_services_user.services.imlp;

import app.ws.bank_services_user.entities.BeneficiaryEntity;
import app.ws.bank_services_user.exceptions.BeneficiaryException;
import app.ws.bank_services_user.repositories.BeneficiaryRepository;
import app.ws.bank_services_user.services.BeneficiaryService;
import app.ws.bank_services_user.shared.dto.BeneficiaryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryUpdateImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Autowired
    public BeneficiaryUpdateImpl(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Override
    public BeneficiaryDto updateBeneficiary(String idBeneficiary, BeneficiaryDto beneficiaryDto) {

        BeneficiaryEntity beneficiaryChecker = beneficiaryRepository.findByBeneficiaryId(idBeneficiary).orElseThrow(
                () -> new BeneficiaryException("beneficiary doesn't exist to update them")
        );
        ModelMapper modelMapper = new ModelMapper();
        BeneficiaryEntity beneficiaryEntityToParse = modelMapper.map(beneficiaryDto, BeneficiaryEntity.class);

        beneficiaryChecker.setDescription(beneficiaryEntityToParse.getDescription());
        beneficiaryChecker.setFirstName(beneficiaryEntityToParse.getFirstName());
        beneficiaryChecker.setLastName(beneficiaryEntityToParse.getLastName());
        beneficiaryChecker.setRib(beneficiaryEntityToParse.getRib());
        BeneficiaryEntity beneficiaryUpdatedEntity = beneficiaryRepository.save(beneficiaryChecker);

        return modelMapper.map(beneficiaryUpdatedEntity, BeneficiaryDto.class);
    }

    @Override
    public BeneficiaryDto createBeneficiary(BeneficiaryDto beneficiaryDto) {
        ModelMapper modelMapper = new ModelMapper();
        BeneficiaryEntity createdBeneficiary = beneficiaryRepository.save(modelMapper.map(beneficiaryDto, BeneficiaryEntity.class));
        return modelMapper.map(createdBeneficiary, BeneficiaryDto.class);
    }

    @Override
    public void deleteBeneficiary(BeneficiaryDto beneficiaryDto) {
        ModelMapper modelMapper = new ModelMapper();
        beneficiaryRepository.findByBeneficiaryId(beneficiaryDto.getBeneficiaryId()).orElseThrow(
                () -> new BeneficiaryException("Beneficiary does not exist to delete them")
        );

        beneficiaryRepository.delete(modelMapper.map(beneficiaryDto, BeneficiaryEntity.class));
    }

    @Override
    public BeneficiaryDto getBeneficiaryById(String id) {
        ModelMapper modelMapper = new ModelMapper();
        BeneficiaryEntity checkedBeneficiary = beneficiaryRepository.findByBeneficiaryId(id).orElseThrow(
                () -> new BeneficiaryException("Beneficiary doesn't exist"));
        return modelMapper.map(checkedBeneficiary, BeneficiaryDto.class);
    }
}
