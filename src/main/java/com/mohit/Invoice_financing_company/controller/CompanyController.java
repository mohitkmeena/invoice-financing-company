package com.mohit.Invoice_financing_company.controller;

import com.mohit.Invoice_financing_company.dto.*;
import com.mohit.Invoice_financing_company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/update-company")
    private ResponseEntity<ResponseDto> update(@RequestHeader("X-User-Id") String companyId, @RequestBody CompanyDto companyDto){
       return companyService.updateCompanyDetails(companyId,companyDto);
    }
    @PostMapping("/verify-pan")
    private ResponseEntity<ResponseDto> verifyPan(@RequestHeader("X-User-Id") String companyId, @RequestBody VerifyPanDto verifyDto){
        return companyService.verifyPan(companyId,verifyDto);
    }
    @PostMapping("/verify-gst")
    private ResponseEntity<ResponseDto> verifyGst(@RequestHeader("X-User-Id") String companyId,@RequestBody VerifyGstInDto verifyDto){
        return companyService.verifyGst(companyId,verifyDto);
    }
    @GetMapping("/company-verified")
    private boolean isCompanyVerified(@RequestHeader("X-User-Id") String companyId){
        return companyService.isCompanyVerified(companyId);
    }


}
