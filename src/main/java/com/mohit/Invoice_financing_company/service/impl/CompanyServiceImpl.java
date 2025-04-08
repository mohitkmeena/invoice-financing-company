package com.mohit.Invoice_financing_company.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.Invoice_financing_company.consumer.CompanyUserDto;
import com.mohit.Invoice_financing_company.dto.*;
import com.mohit.Invoice_financing_company.model.Company;
import com.mohit.Invoice_financing_company.repository.CompanyRepository;
import com.mohit.Invoice_financing_company.service.CompanyService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class CompanyServiceImpl implements CompanyService {


    @Value("${sandbox.api.key}")
    private String apiKey;
    @Autowired
   private CompanyRepository companyRepository;
    @Override
    public void saveCompany(CompanyUserDto companyUserDto) {
          Company company=companyUserDtoToCompany(companyUserDto);
          companyRepository.save(company);
    }

    @Override
    public ResponseEntity<ResponseDto> updateCompanyDetails(String companyId,CompanyDto companyDto) {
        Company company=companyRepository.findByEmail(companyId);
        if(companyDto.getEmail()!=null)company.setEmail(companyDto.getEmail());
        if(companyDto.getPhoneNumber()!=null)company.setPhoneNumber(companyDto.getPhoneNumber());
        if(companyDto.getCompanyName()!=null)company.setCompanyName(companyDto.getCompanyName());
        if(companyDto.getCompanyAddress()!=null)company.setCompanyAddress(companyDto.getCompanyAddress());
        if(companyDto.getPanCard()!=null)company.setPanCard(companyDto.getPanCard());

        if(companyDto.getGstIn()!=null)company.setGstIn(companyDto.getGstIn());
        companyRepository.save(company);
         return new ResponseEntity(ResponseDto.builder()
                         .code(HttpStatus.OK.toString())
                                 .message("company updated successful")
                 .build(), HttpStatus.OK
         );
    }

    @Override
    public ResponseEntity<ResponseDto> verifyPan(String companyId, VerifyPanDto verifyDto)  {
        Company company=companyRepository.findByEmail(companyId);
        try {
            OkHttpClient client = new OkHttpClient();
            String json = objectsTojson(verifyDto);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("https://api.sandbox.co.in/kyc/pan/verify")
                    .post(body)
                    .addHeader("accept", "application/json")
                    .addHeader("x-accept-cache", "true")
                    .addHeader("content-type", "application/json")
                    .addHeader("x-api-key",apiKey)
                    .addHeader("authorization","")

                    .build();

            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper=new ObjectMapper();
        if(response.code()!=200){
            InvalidPanResponse invalidPanResponse=objectMapper.readValue(response.body().string(),InvalidPanResponse.class);
            return new ResponseEntity(ResponseDto.builder()
                    .code("200")
                    .message("please enter a valid pan number")
                    .build(), HttpStatus.NOT_FOUND);
        }

            PanVerificationResponseDto panVerificationResponseDto=objectMapper.readValue(response.body().string(),PanVerificationResponseDto.class);
            if(panVerificationResponseDto.getData().isNameAsPerPanMatch()&&panVerificationResponseDto.getData().getStatus().equals("success")){
                company.setPanCard(verifyDto.getPanCard());
                company.setPanVerified(true);
                company.setVerified(true);
                company.setPanCard(verifyDto.getPanCard());
                companyRepository.save(company);
                return new ResponseEntity(ResponseDto.builder()
                        .code("200")
                        .message("pan verification successful")
                        .build(), HttpStatus.OK);
            }
            else{
                if(!panVerificationResponseDto.getData().isNameAsPerPanMatch()){
                    return new ResponseEntity(ResponseDto.builder()
                            .code("422")
                            .message(" name entered && name in pan card do not match")
                            .build(), HttpStatus.OK);
                }else{
                    return new ResponseEntity(ResponseDto.builder()
                            .code("422")
                            .message(" Pan Verification failed")
                            .build(), HttpStatus.OK);
                }
            }

        }
        catch (IOException e){
            return new ResponseEntity(ResponseDto.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                    .message("pan verification failed")
                    .build(), HttpStatus.OK);
        }

    }


    @Override
    public ResponseEntity<ResponseDto> verifyGst(String companyId,VerifyGstInDto verifyDto) {
        try{
            OkHttpClient client = new OkHttpClient();
             MediaType mediaType = MediaType.parse("application/json");
            String json = objectsTojson(verifyDto);
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("https://api.sandbox.co.in/gst/compliance/public/gstin/search")
                    .post(body)
                    .addHeader("accept", "application/json")
                    .addHeader("x-accept-cache", "true")
                    .addHeader("content-type", "application/json")
                    .addHeader("x-api-key",apiKey)
                    .addHeader("authorization","")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.body() != null) {
                String jsonString = response.body().string();
                ObjectMapper mapper = new ObjectMapper();

                GSTRawResponse rawResponse = mapper.readValue(jsonString, GSTRawResponse.class);


                if (rawResponse.getCode() == 200 && rawResponse.getData() != null) {
                    JsonNode dataNode = mapper.convertValue(rawResponse.getData(), JsonNode.class);

                    if (dataNode.has("status_cd") && dataNode.get("status_cd").asText().equals("1")) {
                        GSTSuccessDataWrapper successData = mapper.treeToValue(dataNode, GSTSuccessDataWrapper.class);
                        Company company=companyRepository.findByEmail(companyId);
                        if( successData.getData().getLgnm()==company.getCompanyName()||successData.getData().getTradeNam()==company.getCompanyName()){
                        company.setGstInVerified(true);
                            company.setVerified(true);
                            company.setGstIn(verifyDto.getGstin());
                        companyRepository.save(company);
                    }

                    } else if (dataNode.has("status_cd") && dataNode.get("status_cd").asText().equals("0")) {

                        return new ResponseEntity(ResponseDto.builder()
                                .code("200")
                                .message("gst Record not found")
                                .build(), HttpStatus.OK);
                    }
                } else if (rawResponse.getCode() == 422) {
                    return new ResponseEntity(ResponseDto.builder()
                            .code("422")
                            .message("INVALID GSTIN PATTERN")
                            .build(), HttpStatus.NOT_FOUND);
                }
            }




        }catch (IOException e){
            return new ResponseEntity(ResponseDto.builder()
                    .code("404")
                    .message("Error in verifying gst")
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ResponseDto.builder()
                .code("404")
                .message("Error in verifying gst please try again")
                .build(), HttpStatus.NOT_FOUND);

    }

    private Company companyUserDtoToCompany(CompanyUserDto companyUserDto){
        return Company.builder()
                .email(companyUserDto.getEmail())
                .phoneNumber(companyUserDto.getPhoneNumber())
        .build();

    }
    private String objectsTojson(Object object){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean isCompanyVerified(String companyId){
        Company company=companyRepository.findByEmail(companyId);
        return company.isVerified();
    }



}
