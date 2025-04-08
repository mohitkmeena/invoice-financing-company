package com.mohit.Invoice_financing_company.repository;

import com.mohit.Invoice_financing_company.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice,String> {

}
