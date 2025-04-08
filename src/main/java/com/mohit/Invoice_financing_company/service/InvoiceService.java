package com.mohit.Invoice_financing_company.service;

import com.mohit.Invoice_financing_company.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    public ResponseEntity<ResponseDto> addInvoice(String CompanyId);
}
