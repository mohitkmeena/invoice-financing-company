package com.mohit.Invoice_financing_company.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mohit.Invoice_financing_company.model.Invoice;
import com.mohit.Invoice_financing_company.model.Promoter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CompanyDto {

    private  String companyName;
    private String companyAddress;
    private Set<Promoter> promoters;
    private String panCard;
    private String gstIn;

    private String logo;
    private boolean isPanVerified;
    private boolean isGstInVerified;
    private String email;

    private String phoneNumber;
    private Set<Invoice>invoices;
}
