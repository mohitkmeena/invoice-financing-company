package com.mohit.Invoice_financing_company.service;

import com.mohit.Invoice_financing_company.dto.PromoterDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PromoterService {
    public ResponseEntity<ResponseDto> addPromoter(String companyId,PromoterDto promoterDto);
    public ResponseEntity<ResponseDto> removePromoter(String companyId,PromoterDto promoterDto);
    public ResponseEntity<ResponseDto> updatePromoter(String companyId,PromoterDto promoterDto);

}
