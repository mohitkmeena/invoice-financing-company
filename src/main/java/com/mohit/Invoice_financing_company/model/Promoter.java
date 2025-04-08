package com.mohit.Invoice_financing_company.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Entity
@RequiredArgsConstructor
@Table(name = "promoter")
@Getter
@Setter
@Builder
public class Promoter {
    private String name;
    @Id
    private String panCard;
     private String address;
     private String phoneNumber;
     @Email(message = "enter valid email")
     private String email;


}
