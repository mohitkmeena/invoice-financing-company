package com.mohit.Invoice_financing_company.service;

import com.mohit.Invoice_financing_company.consumer.CompanyUserDto;
import com.mohit.Invoice_financing_company.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CompanyService {
    void saveCompany(CompanyUserDto companyUserDto);
    ResponseEntity<ResponseDto> updateCompanyDetails(String companyId,CompanyDto companyDto);
    ResponseEntity<ResponseDto> verifyPan(String companyId, VerifyPanDto verifyDto);
    ResponseEntity<ResponseDto> verifyGst(String companyId, VerifyGstInDto verifyDto);
    public boolean isCompanyVerified(String companyId);

}
