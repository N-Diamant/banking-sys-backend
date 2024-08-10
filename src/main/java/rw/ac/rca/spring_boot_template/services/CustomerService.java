package rw.ac.rca.spring_boot_template.services;


import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.models.Customer;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(CreateCustomerDTO customer);
    Optional<Customer> findCustomerById(Long id);
    Optional<Customer> findCustomerByEmail(String email);
    Customer updateCustomer(Long id, Customer customerDetails);
}