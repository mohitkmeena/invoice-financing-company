package com.mohit.Invoice_financing_company.dto;


import lombok.Data;

@Data
public class InvoiceDto {

    private int interestRate;
    private  int totalAmount;
    private int minimumAmount;
    private int remainingAmount;
    private String multipartFile;
}
