package com.mohit.Invoice_financing_company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@RequiredArgsConstructor
@Table(name = "promoter")
public class Promoter {
    private String name;
    @Id
    private String panCard;
     private String address;


}
