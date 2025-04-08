package com.mohit.Invoice_financing_company.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PanVerificationResponseDto {
    private int code;
    private long timestamp;

    @JsonProperty("transaction_id")
    private String transactionId;

    private PanVerificationDataDto data;
}
