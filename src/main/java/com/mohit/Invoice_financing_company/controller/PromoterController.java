package com.mohit.Invoice_financing_company.controller;

import com.mohit.Invoice_financing_company.dto.PromoterDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import com.mohit.Invoice_financing_company.service.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/v1/promoters")
public class PromoterController {
    @Autowired private PromoterService promoterService;

    @PostMapping("/add-promoter")
    public ResponseEntity<ResponseDto> addPromoter(@RequestHeader(name = "X-User-ID") String companyId,@RequestBody PromoterDto promoterDto ){
        return promoterService.addPromoter(companyId,promoterDto);

    }
    @DeleteMapping("/delete-promoter")
    public ResponseEntity<ResponseDto>removePromoter(@RequestHeader(name = "X-User-ID") String companyId,@RequestBody PromoterDto promoterDto ){
        return promoterService.removePromoter(companyId,promoterDto);
    }
    @PutMapping("/update-promoter")
    public ResponseEntity<ResponseDto>updatePromoter(@RequestHeader(name = "X-User-ID") String companyId,@RequestBody PromoterDto promoterDto ){
        return promoterService.removePromoter(companyId,promoterDto);
    }
    @GetMapping("/get-promoters")
    public List<PromoterDto> getPromoters(@RequestHeader(name = "X-User-ID") String companyId){
        return promoterService.getPromoters(companyId);
    }


}
