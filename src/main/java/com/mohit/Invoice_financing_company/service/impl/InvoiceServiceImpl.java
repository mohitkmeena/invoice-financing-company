package com.mohit.Invoice_financing_company.service.impl;

import com.mohit.Invoice_financing_company.dto.InvoiceDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import com.mohit.Invoice_financing_company.model.Company;
import com.mohit.Invoice_financing_company.model.Invoice;
import com.mohit.Invoice_financing_company.repository.CompanyRepository;
import com.mohit.Invoice_financing_company.repository.InvoiceRepository;
import com.mohit.Invoice_financing_company.service.CompanyService;
import com.mohit.Invoice_financing_company.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvoiceServiceImpl implements InvoiceService {
   @Autowired
   private InvoiceRepository invoiceRepository;
   @Autowired private CompanyRepository companyRepository;
    @Override
    public ResponseEntity<ResponseDto> addInvoice(String companyId, InvoiceDto invoiceDto) {
        Company company=companyRepository.findByEmail(companyId);
        Invoice invoice=invoiceDtoToInvoice(invoiceDto);
        invoice.setCompany(company);
        company.getInvoices().add(invoice);
        companyRepository.save(company);
        invoice=invoiceRepository.save(invoice);
        return new ResponseEntity<>(ResponseDto.builder()
                .message("invoice added with invoice id "+ invoice.getInvoiceId())
                .code("200")
                .build(), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<ResponseDto> updateInvoice(String CompanyId, InvoiceDto invoiceDto) {
        Invoice invoice=invoiceRepository.getById(invoiceDto.getInvoiceId());
        if(invoice.getCompany().getEmail().equals(CompanyId)){

            invoice.setLastDate(invoiceDto.getLastDate());
            invoice.setInterestRate(invoiceDto.getInterestRate());
            invoice.setMinimumAmount(invoiceDto.getMinimumAmount());
            invoiceRepository.save(invoice);
            return new ResponseEntity<>(ResponseDto.builder()
                    .message("invoice updated with invoice id "+ invoice.getInvoiceId())
                    .code("200")
                    .build(), HttpStatus.OK) ;
        }
        return new ResponseEntity<>(ResponseDto.builder()
                .message("error updating invoice with invoice id "+ invoice.getInvoiceId())
                .code("403")
                .build(), HttpStatus.BAD_REQUEST) ;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteInvoice(String companyId, InvoiceDto invoiceDto) {
        Company company=companyRepository.findByEmail(companyId);

        invoiceRepository.deleteByInvoiceIdAndCompany(invoiceDto.getInvoiceId(),company);
        return   new ResponseEntity<>(ResponseDto.builder()
                .message("invoice deleted with invoice id "+ invoiceDto.getInvoiceId())
                .code("200")
                .build(), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<InvoiceDto> getInvoice(String CompanyId, InvoiceDto invoiceDto) {
        Company company=companyRepository.findByEmail(CompanyId);
        Invoice invoice=invoiceRepository.getByInvoiceIdAndCompany(invoiceDto.getInvoiceId(),company);
        invoiceToInvoiceDto(invoiceDto,invoice);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK) ;
    }

    @Override
    public List<InvoiceDto> getAllInvoices(String companyId) {
        Company company=companyRepository.findByEmail(companyId);
        List<Invoice> invoices=invoiceRepository.findByCompany(company);
         return invoices.stream().map(this::invoiceToInvoiceDto).collect(Collectors.toList());

    }
    private Invoice invoiceDtoToInvoice(InvoiceDto invoiceDto){
        return Invoice.builder()
                .interestRate(invoiceDto.getInterestRate())
                .lastDate(invoiceDto.getLastDate())
                .remainingAmount(invoiceDto.getTotalAmount())
                .totalAmount(invoiceDto.getTotalAmount())
                .minimumAmount(invoiceDto.getMinimumAmount())
                .isVerified(false)
                .multipartFile(invoiceDto.getMultipartFile())
                .build();
    }
    private InvoiceDto invoiceToInvoiceDto(InvoiceDto invoiceDto, Invoice invoice) {
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setInterestRate(invoice.getInterestRate());
        invoiceDto.setTotalAmount(invoice.getTotalAmount());
        invoiceDto.setMinimumAmount(invoice.getMinimumAmount());
        invoiceDto.setRemainingAmount(invoice.getRemainingAmount());
        invoiceDto.setMultipartFile(invoice.getMultipartFile());
        invoiceDto.setLastDate(invoice.getLastDate());
        return invoiceDto;
}
    private InvoiceDto invoiceToInvoiceDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setInterestRate(invoice.getInterestRate());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setMinimumAmount(invoice.getMinimumAmount());
        dto.setRemainingAmount(invoice.getRemainingAmount());
        dto.setMultipartFile(invoice.getMultipartFile());
        dto.setLastDate(invoice.getLastDate());
        return dto;
    }


}
