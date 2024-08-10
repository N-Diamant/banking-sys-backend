package rw.ac.rca.spring_boot_template.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.repositories.ICustomerRepository;
import rw.ac.rca.spring_boot_template.services.CustomerService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobile(customerDTO.getMobile());
        customer.setDateOfBirth(new Date(customerDTO.getDateOfBirth().getTime()));
        customer.setAccount(customerDTO.getAccount());
        customer.setBalance(customerDTO.getBalance().doubleValue());
        customer.setLastUpdatedDateTime(LocalDateTime.now());

        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("email")) {
                    throw new RuntimeException("Customer with this email already exists");
                } else if (e.getMessage().contains("account")) {
                    throw new RuntimeException("Customer with this account number already exists");
                } else if (e.getMessage().contains("mobile")) {
                    throw new RuntimeException("Customer with this mobile number already exists");
                }
            }
            throw e; // Re-throw if it's not a duplicate entry exception
        }
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setEmail(customerDetails.getEmail());
        customer.setMobile(customerDetails.getMobile());
//        customer.setDob(customerDetails.getDob());
        customer.setAccount(customerDetails.getAccount());
        customer.setBalance(customerDetails.getBalance());
        return customerRepository.save(customer);
    }
}
