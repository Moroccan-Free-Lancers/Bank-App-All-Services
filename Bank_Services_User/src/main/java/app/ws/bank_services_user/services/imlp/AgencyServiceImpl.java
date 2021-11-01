package app.ws.bank_services_user.services.imlp;

import app.ws.bank_services_user.entities.AgencyEntity;
import app.ws.bank_services_user.exceptions.AgencyException;
import app.ws.bank_services_user.repositories.AgencyRepository;
import app.ws.bank_services_user.services.AgencyService;
import app.ws.bank_services_user.shared.Utils;
import app.ws.bank_services_user.shared.dto.AgencyDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    Utils utils;

    @Override
    public AgencyDto createAgency(AgencyDto agencyDto) {

        AgencyEntity checkerId = agencyRepository.findByAgenceId(agencyDto.getAgenceId());

        AgencyEntity checkerName = agencyRepository.findByNomAgence(agencyDto.getNomAgence());

        AgencyEntity checkerAdress = agencyRepository.findByAdresseAgence(agencyDto.getAdresseAgence());

        AgencyEntity checkerResponsable = agencyRepository.findByResponsableAgence(agencyDto.getResponsableAgence());

        AgencyEntity checkerTelephone = agencyRepository.findByTelephoneAgence(agencyDto.getTelephoneAgence());
        if ((checkerTelephone != null) || (checkerResponsable != null) || (checkerAdress != null) || (checkerName != null) || (checkerId != null))
            throw new AgencyException("Agency already exists");

        AgencyEntity agencyEntity = new AgencyEntity();

        BeanUtils.copyProperties(agencyDto, agencyEntity);

        agencyEntity.setAgenceId(utils.generatedStringId(32));

        AgencyEntity agencyCreated = agencyRepository.save(agencyEntity);

        AgencyDto agencyDto1 = new AgencyDto();

        BeanUtils.copyProperties(agencyCreated, agencyDto1);

        return agencyDto1;
    }

    @Override
    public AgencyDto printAgency(String id) {

        AgencyEntity checkerId = agencyRepository.findByAgenceId(id);

        AgencyDto agencyDto = new AgencyDto();

        BeanUtils.copyProperties(checkerId, agencyDto);

        return agencyDto;
    }

    @Override
    public String removeAgency(String id) {

        AgencyEntity agency = agencyRepository.findByAgenceId(id);

        if (agency == null) throw new AgencyException("Agency Doesnt Exist");

        agencyRepository.delete(agency);

        return "agency Deleted";
    }
}
