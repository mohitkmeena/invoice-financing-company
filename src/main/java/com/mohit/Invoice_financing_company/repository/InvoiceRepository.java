package com.mohit.Invoice_financing_company.repository;

import com.mohit.Invoice_financing_company.model.Company;
import com.mohit.Invoice_financing_company.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice,String> {

    Optional<Invoice> findByIdAndCompany(String invoiceId, Company company);
    @Query("DELETE FROM Invoice i WHERE i.invoiceId = :invoiceId AND i.company = :company")
    void deleteByInvoiceIdAndCompany(@Param("invoiceId") String invoiceId, @Param("company") Company company);
    @Query("SELECT i FROM Invoice i WHERE i.invoiceId = :invoiceId AND i.company = :company")
    Invoice getByInvoiceIdAndCompany(@Param("invoiceId") String invoiceId, @Param("company") Company company);

    List<Invoice> findByCompany(Company company);
}
