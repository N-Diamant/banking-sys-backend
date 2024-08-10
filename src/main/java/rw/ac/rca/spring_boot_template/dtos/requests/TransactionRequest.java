package rw.ac.rca.spring_boot_template.dtos.requests;


import lombok.Data;

@Data

public class TransactionRequest {
    private Long customerId;
    private Double amount;
    private Long destinationAccountId; // Used only for transfers




}
