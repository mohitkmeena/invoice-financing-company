package com.mohit.Invoice_financing_company.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@Table (name = "company")
@AllArgsConstructor
public class Company{
    @Id
    private String companyId;
    private  String companyName;
    private String companyAddress;
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "company_promoter", joinColumns = @JoinColumn(name = "companyId"), inverseJoinColumns = @JoinColumn(name = "panCard"))
    private Set<Promoter> promoters;
    private String panCard;
    private String gstIn;

    private String logo;
    private boolean isPanVerified;

    private boolean isGstInVerified;
    @Email
    private String email;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Invoice>invoices;
    private boolean isVerified;
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    @NotBlank(message = "Bank details cannot be blank")
    private BankDetails bankDetails;
    @PrePersist
    public void setisVerified(){
        this.isPanVerified=false;
        this.isGstInVerified=false;
        this.isVerified=false;
    }
}
