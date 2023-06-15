package luis122448.projectpizza.service;

import luis122448.projectpizza.persistence.entity.CustomerEntity;
import luis122448.projectpizza.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }

}
