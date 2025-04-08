package com.mohit.Invoice_financing_company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GSTSuccessDataWrapper {
    private GSTFullData data;

    @JsonProperty("status_cd")
    private String statusCd;
}
