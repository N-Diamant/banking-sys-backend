package rw.ac.rca.spring_boot_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.dtos.requests.CreateCustomerDTO;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.services.CustomerService;
import rw.ac.rca.spring_boot_template.utils.ApiResponse;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Validated
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CreateCustomerDTO customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.findCustomerByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Set<String> errorMessages = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessages.add(error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, errorMessages));
    }

//    @GetMapping("/")
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//        List<Customer> customers = customerService.findAll();
//        return ResponseEntity.ok(customers);
//    }
}
