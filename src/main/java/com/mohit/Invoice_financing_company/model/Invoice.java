package com.mohit.Invoice_financing_company.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.annotation.InterfaceStability;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "invoices")
public class Invoice {
    @Id
    private String invoiceId;
    private boolean isVerified;
    private  int totalAmount;
    private int minimumAmount;
    private int remainingAmount;
    private String multipartFile;
    private Date lastDate;
    private int interestRate;
    @ManyToOne
    @JoinColumn(name = "companyId",referencedColumnName = "companyId")
    private Company company;
    @PrePersist
    public void setisVerified(){
        this.isVerified=false;
    }
}
