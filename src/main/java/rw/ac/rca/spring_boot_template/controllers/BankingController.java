package rw.ac.rca.spring_boot_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.spring_boot_template.dtos.requests.TransactionDTO;
import rw.ac.rca.spring_boot_template.dtos.requests.TransactionRequest;
import rw.ac.rca.spring_boot_template.models.Transaction;
import rw.ac.rca.spring_boot_template.services.BankingService;
import rw.ac.rca.spring_boot_template.utils.ApiResponse;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionDTO request) {
        try {
            Transaction transaction = bankingService.deposit(request.getCustomerId(), request.getAmount());
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionDTO request) {
        try {
            Transaction transaction = bankingService.withdraw(request.getCustomerId(), request.getAmount());
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Set<String> errorMessages = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessages.add(error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, errorMessages));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransactionRequest request) {
        try {
            if (request.getDestinationAccountId() == null) {
                return ResponseEntity.badRequest().body("Destination account ID is required");
            }
            Transaction transaction = bankingService.transfer(request.getCustomerId(), request.getDestinationAccountId(), request.getAmount());
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
