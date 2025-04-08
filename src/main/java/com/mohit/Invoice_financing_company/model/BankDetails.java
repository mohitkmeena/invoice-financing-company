package com.mohit.Invoice_financing_company.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "companyId")
    private Company company;

    private String bankName;
    private String bankAccount;
    private String ifscCode;
}
