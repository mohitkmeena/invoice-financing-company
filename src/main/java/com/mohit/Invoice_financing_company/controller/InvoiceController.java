package com.mohit.Invoice_financing_company.controller;

import com.mohit.Invoice_financing_company.dto.InvoiceDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import com.mohit.Invoice_financing_company.service.CompanyService;
import com.mohit.Invoice_financing_company.service.FileService;
import com.mohit.Invoice_financing_company.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/company/v1/invoices")
public class InvoiceController {
     @Autowired private InvoiceService invoiceService;
     @Autowired private FileService fileService;
     @Autowired private CompanyService companyService;

     @PostMapping("/add-invoice")
    public ResponseEntity<ResponseDto> addInvoice(@RequestHeader("X-User-Id") String companyId, @RequestBody InvoiceDto invoiceDto, @RequestPart MultipartFile file) {
         boolean isCompanyVerified=companyService.isCompanyVerified(companyId);
         if(!isCompanyVerified){
             return new ResponseEntity<>(ResponseDto.builder().message("Company is not verified please verify company")
                     .code("403")
                     .build(), HttpStatus.BAD_REQUEST);
         }
         String name=fileService.uploadInvoice(file,companyId);
         invoiceDto.setMultipartFile(name);
          return invoiceService.addInvoice(companyId,invoiceDto);


     }
    @DeleteMapping("/delete-invoice")
    public ResponseEntity<ResponseDto> deleteInvoice(@RequestHeader("X-User-Id") String companyId, @RequestBody InvoiceDto invoiceDto) {
         if(Objects.isNull(invoiceDto.getInvoiceId())){
             return new ResponseEntity<>(
                     ResponseDto.builder()
                             .message("invoice number must be valid")
                             .code("404")
                             .build(),
             HttpStatus.NOT_ACCEPTABLE
                     );
         }
       return invoiceService.deleteInvoice(companyId,invoiceDto);

    }
    @PostMapping("/update-invoice")
    public ResponseEntity<ResponseDto> updateInvoice(@RequestHeader("X-User-Id") String companyId, @RequestBody InvoiceDto invoiceDto) {
        if(Objects.isNull(invoiceDto.getInvoiceId())){
            return new ResponseEntity<>(
                    ResponseDto.builder()
                            .message("invoice number must be valid")
                            .code("404")
                            .build(),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
       return invoiceService.updateInvoice(companyId,invoiceDto);

    }
    @GetMapping("/getAllInvoices")
    public List<InvoiceDto> getAllInvoices(@RequestHeader("X-User-Id") String companyId){
        return invoiceService.getAllInvoices(companyId);
    }
    @GetMapping("/get-invoice")
    public  ResponseEntity<InvoiceDto> getInvoice(@RequestHeader("X-User-Id") String companyId,@RequestBody InvoiceDto invoiceDto){
        if(Objects.isNull(invoiceDto.getInvoiceId())){
            return null;

        }
         return invoiceService.getInvoice(companyId,invoiceDto);
    }

}
