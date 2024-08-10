package rw.ac.rca.spring_boot_template.dtos.requests;


import lombok.Data;
import rw.ac.rca.spring_boot_template.enumerations.TransactionType;

@Data
public class TransactionDTO {
    // Getters and Setters
    private Long customerId;
    private Double amount;
   // Optional, used for transfers

    // Constructors
    public TransactionDTO() {
    }

    public TransactionDTO(Long customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;

    }




}
