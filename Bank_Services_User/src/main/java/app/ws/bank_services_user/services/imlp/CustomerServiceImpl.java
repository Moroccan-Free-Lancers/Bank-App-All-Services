package app.ws.bank_services_user.services.imlp;
import app.ws.bank_services_user.entities.CustomerEntity;
import app.ws.bank_services_user.exceptions.CustomerException;
import app.ws.bank_services_user.repositories.CustomerRepository;
import app.ws.bank_services_user.services.CustomerService;
import app.ws.bank_services_user.shared.Utils;
import app.ws.bank_services_user.shared.dto.CustomerDto;
import app.ws.bank_services_user.shared.dto.ProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;



@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder bankPasswordEncoder;

    private final Utils util;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder bankPasswordEncoder, Utils util) {
        this.customerRepository = customerRepository;
        this.bankPasswordEncoder = bankPasswordEncoder;
        this.util = util;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        if(customerRepository.findByMail(customerDto.getMail()).isPresent())
            throw new CustomerException("Customer not found !");


        if (customerDto.getProfiles().size() > 0) {
            for (ProfileDto profile : customerDto.getProfiles()) {
                profile.setCustomer(customerDto);
            }
        }
        ModelMapper modelMapper = new ModelMapper();
        customerDto.setCustomerId(util.generatedStringId(30));
        customerDto.setEncryptPassword(bankPasswordEncoder.encode(customerDto.getPassword()));
        customerDto.setBeneficiaries(new ArrayList<>());
        customerDto.setCounselor(null);
        CustomerEntity customerToCreated = modelMapper.map(customerDto, CustomerEntity.class);
        CustomerEntity customerEntityCreated = customerRepository.save(customerToCreated);
        return modelMapper.map(customerEntityCreated, CustomerDto.class);
    }


    @Override
    public CustomerDto getCustomerById(String id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerRepository.findByCustomerId(id).orElseThrow(
                () -> new CustomerException("Customer not found !")
        ), CustomerDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CustomerEntity customerEntity = customerRepository.findByMail(email).orElseThrow(
                () -> new UsernameNotFoundException("Customer not found !")
        );
        return new User(customerEntity.getMail(), customerEntity.getEncryptPassword(), new ArrayList<>());
    }
}
