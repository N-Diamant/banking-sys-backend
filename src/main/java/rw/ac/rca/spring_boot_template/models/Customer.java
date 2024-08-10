package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"mobile"}),
        @UniqueConstraint(columnNames = {"account"})
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstname;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    @Column(name = "mobile", unique = true)
    private String mobile;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @NotBlank(message = "Account number cannot be blank")
    @Size(min = 6, max = 20, message = "Account number must be between 6 and 20 characters")
    @Column(name = "account", unique = true)
    private String account;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than 0")
    private Double balance;

    private LocalDateTime lastUpdatedDateTime;

    public Customer() {
        // Default constructor
    }

    public Customer(String firstname, String lastname, String email, String mobile, Date dateOfBirth, String account, Double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        this.balance = balance;
        this.lastUpdatedDateTime = LocalDateTime.now();
    }

}
