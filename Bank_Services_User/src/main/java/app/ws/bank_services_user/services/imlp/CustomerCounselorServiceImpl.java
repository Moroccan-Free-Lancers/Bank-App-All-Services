package app.ws.bank_services_user.services.imlp;

import app.ws.bank_services_user.entities.CounselorEntity;
import app.ws.bank_services_user.entities.CustomerEntity;
import app.ws.bank_services_user.exceptions.CounselorException;
import app.ws.bank_services_user.exceptions.CustomerException;
import app.ws.bank_services_user.repositories.CounselorRepository;
import app.ws.bank_services_user.repositories.CustomerRepository;
import app.ws.bank_services_user.services.CustomerCounselorService;
import app.ws.bank_services_user.shared.Utils;
import app.ws.bank_services_user.shared.dto.CounselorDto;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import app.ws.bank_services_user.shared.dto.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerCounselorServiceImpl implements CustomerCounselorService {

    private final CustomerRepository customerRepository;

    private final CounselorRepository counselorRepository;

    private final Utils util;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerCounselorServiceImpl(CustomerRepository customerRepository, CounselorRepository counselorRepository, Utils util, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.counselorRepository = counselorRepository;
        this.util = util;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public CustomerDto createCustomerWithCounselor(CustomerDto customerDto, String counselorId) {

        if(customerRepository.findByMail(customerDto.getMail()).isPresent())
            throw new CustomerException("Customer already exist");

        if (customerDto.getProfiles().size() > 0) {
            for (ProfileDto profile : customerDto.getProfiles()) {
                profile.setCustomer(customerDto);
            }
        }
        ModelMapper modelMapper = new ModelMapper();
        customerDto.setCustomerId(util.generatedStringId(30));
        customerDto.setEncryptPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
        customerDto.setBeneficiaries(new ArrayList<>());
        CounselorEntity counselor = counselorRepository.findByCounselorId(counselorId).orElseThrow(
                () -> new CounselorException("Counselor doesn't exist to affect them to customer")
        );
        CounselorDto counselorDto = modelMapper.map(counselor, CounselorDto.class);
        customerDto.setCounselor(counselorDto);
        CustomerEntity customerToCreated = modelMapper.map(customerDto, CustomerEntity.class);
        CustomerEntity customerEntityCreated = customerRepository.save(customerToCreated);
        return modelMapper.map(customerEntityCreated, CustomerDto.class);
    }

}
