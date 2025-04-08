package com.mohit.Invoice_financing_company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GSTRawResponse {
    private int code;
    private long timestamp;

    @JsonProperty("transaction_id")
    private String transactionId;

    private Object data; // Will manually deserialize later based on content
    private String message; // for 422 or generic error
}

