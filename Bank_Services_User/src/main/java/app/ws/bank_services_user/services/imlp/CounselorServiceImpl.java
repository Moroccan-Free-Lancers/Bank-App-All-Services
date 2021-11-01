package app.ws.bank_services_user.services.imlp;

import app.ws.bank_services_user.entities.CounselorEntity;
import app.ws.bank_services_user.entities.CustomerEntity;
import app.ws.bank_services_user.exceptions.CounselorException;
import app.ws.bank_services_user.repositories.CounselorRepository;
import app.ws.bank_services_user.services.CounselorService;
import app.ws.bank_services_user.shared.Utils;
import app.ws.bank_services_user.shared.dto.CounselorDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CounselorServiceImpl implements CounselorService {

    private final CounselorRepository counselorRepository;
    private final Utils util;

    @Autowired
    public CounselorServiceImpl(CounselorRepository counselorRepository, Utils util) {
        this.counselorRepository = counselorRepository;
        this.util = util;
    }

    @Override
    public CounselorDto createCounselor(CounselorDto counselorDto) {

        if(counselorRepository.findByMail(counselorDto.getMail()).isPresent())
            throw new CounselorException("Counselor already exist");

        ModelMapper modelMapper = new ModelMapper();

        counselorDto.setCounselorId(util.generatedStringId(30));

        CounselorEntity counselorToCreate = modelMapper.map(counselorDto, CounselorEntity.class);

        CounselorEntity counselorCreated = counselorRepository.save(counselorToCreate);

        return modelMapper.map(counselorCreated, CounselorDto.class);
    }

    @Override
    public CounselorDto updateCounselor(CounselorDto counselorDto, String counselorId) {

        CounselorEntity counselorChecker = counselorRepository.findByCounselorId(counselorId).orElseThrow(
                () -> new CounselorException("Counselor not found !")
        );

        counselorChecker.setMail(counselorDto.getMail());
        counselorChecker.setAgency(counselorDto.getAgency());
        counselorChecker.setAgencyAdresse(counselorDto.getAgencyAdresse());
        counselorChecker.setPhoneNumber(counselorDto.getPhoneNumber());
        counselorChecker.setUsername(counselorDto.getUsername());
        Set<CustomerEntity> customers = new HashSet<>();
        ModelMapper modelMapper = new ModelMapper();
        if ( counselorDto.getCustomers() != null &&  counselorDto.getCustomers().size() > 0){
            for (CustomerDto custom : counselorDto.getCustomers()) {
                customers.add(modelMapper.map(custom, CustomerEntity.class));
            }
        }
        counselorChecker.setCustomers(customers);

        CounselorEntity counselorUpdated = counselorRepository.save(counselorChecker);

        return modelMapper.map(counselorUpdated, CounselorDto.class);
    }
}
