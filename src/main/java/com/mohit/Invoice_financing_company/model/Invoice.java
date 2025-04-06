package com.mohit.Invoice_financing_company.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Invoice {
    @Id
    private String invoiceId;
    private boolean isVerified;
    private  int totalAmount;
    private int minimumAmount;
    private int remainingAmount;
    private String multipartFile;
    @ManyToOne
    @JoinColumn(name = "companyId",referencedColumnName = "companyId")
    private Company company;

}
