package com.mohit.Invoice_financing_company.service.impl;

import com.mohit.Invoice_financing_company.dto.PromoterDto;
import com.mohit.Invoice_financing_company.dto.ResponseDto;
import com.mohit.Invoice_financing_company.model.Company;
import com.mohit.Invoice_financing_company.model.Promoter;
import com.mohit.Invoice_financing_company.repository.CompanyRepository;
import com.mohit.Invoice_financing_company.repository.PromoterRepository;
import com.mohit.Invoice_financing_company.service.PromoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromoterServiceImpl implements PromoterService {

    private final CompanyRepository companyRepository;
    private final PromoterRepository promoterRepository;

    @Override
    public ResponseEntity<ResponseDto> addPromoter(String companyId, PromoterDto promoterDto) {
        Company company = companyRepository.findById(companyId).get();  // safe since company always exists

        Promoter promoter = dtoToPromoter(promoterDto);
        company.getPromoters().add(promoter);

        promoterRepository.save(promoter);
        companyRepository.save(company);

        return ResponseEntity.ok(new ResponseDto("200","Promoter added successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> removePromoter(String companyId, PromoterDto promoterDto) {
        Company company = companyRepository.findById(companyId).get();  // safe

        Promoter promoter = promoterRepository.findById(promoterDto.getPanCard())
                .orElse(null);

        if (promoter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto("404","Promoter not found"));
        }

        company.getPromoters().remove(promoter);
        companyRepository.save(company);

        return ResponseEntity.ok(new ResponseDto("200","Promoter removed successfully"));
    }

    @Override
    public ResponseEntity<PromoterDto> updatePromoter(String companyId, PromoterDto promoterDto) {
        Promoter promoter = promoterRepository.findById(promoterDto.getPanCard())
                .orElse(null);

        if (promoter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        promoter.setName(promoterDto.getName());

        promoter.setEmail(promoterDto.getEmail());
        promoter.setPhoneNumber(promoterDto.getPhoneNumber());

        promoterRepository.save(promoter);
        return ResponseEntity.ok(promoterToDto(promoter));
    }

    @Override
    public List<PromoterDto> getPromoters(String companyId) {
        Company company = companyRepository.findById(companyId).get();  // safe

        return company.getPromoters()
                .stream()
                .map(this::promoterToDto)
                .collect(Collectors.toList());
    }

    // --- DTO ↔ Entity conversion helpers ---

    private Promoter dtoToPromoter(PromoterDto dto) {
        return Promoter.builder()
                .panCard(dto.getPanCard())
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    private PromoterDto promoterToDto(Promoter promoter) {
        PromoterDto dto = new PromoterDto();
        dto.setPanCard(promoter.getPanCard());
        dto.setName(promoter.getName());
        dto.setEmail(promoter.getEmail());
        dto.setPhoneNumber(promoter.getPhoneNumber());
        return dto;
    }
}