package com.mohit.Invoice_financing_company.service;

import com.mohit.Invoice_financing_company.dto.InvoiceDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface InvoiceService {
    public ResponseEntity<ResponseDto> addInvoice(String CompanyId, InvoiceDto invoiceDto);
    public ResponseEntity<ResponseDto> updateInvoice(String CompanyId, InvoiceDto invoiceDto);
    public ResponseEntity<ResponseDto> deleteInvoice(String CompanyId, InvoiceDto invoiceDto);
    public ResponseEntity<InvoiceDto> getInvoice(String CompanyId, InvoiceDto invoiceDto);
    public List<InvoiceDto> getAllInvoices(String CompanyId);

}
