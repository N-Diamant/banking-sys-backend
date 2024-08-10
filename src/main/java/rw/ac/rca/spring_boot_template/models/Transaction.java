package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import rw.ac.rca.spring_boot_template.enumerations.TransactionStatus;
import rw.ac.rca.spring_boot_template.enumerations.TransactionType;

import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

// Ensure table name consistency
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private String account;

//    @Column(nullable = false, precision = 19)?
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TransactionType type;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDateTime;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private LocalDateTime transactionDateTime;

}
