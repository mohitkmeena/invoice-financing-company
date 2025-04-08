package com.mohit.Invoice_financing_company.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PromoterDto {
   private String name;
   private String panCard;
    private String address;
    private String email;
    private String phoneNumber;
}
