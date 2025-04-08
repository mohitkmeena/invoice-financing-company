package com.mohit.Invoice_financing_company.repository;

import com.mohit.Invoice_financing_company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {

    Company findByEmail(String companyId);
}
