package com.mohit.Invoice_financing_company.repository;

import com.mohit.Invoice_financing_company.model.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter,String> {
}
