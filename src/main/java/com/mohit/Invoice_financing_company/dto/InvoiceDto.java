package com.mohit.Invoice_financing_company.dto;


import com.mohit.Invoice_financing_company.model.BankDetails;
import lombok.Data;

import java.util.Date;

@Data
public class InvoiceDto {
    private String invoiceId;
    private int interestRate;
    private  int totalAmount;
    private int minimumAmount;
    private int remainingAmount;
    private String multipartFile;
    private Date lastDate;
    private BankDetails bankDetails;
}
