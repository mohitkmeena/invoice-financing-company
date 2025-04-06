package com.mohit.Invoice_financing_company.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.Invoice_financing_company.dto.CompanyDto;
import org.apache.kafka.common.serialization.Deserializer;


public class CompanyDeserializer implements Deserializer<CompanyDto> {
    @Override
    public CompanyDto deserialize(String arg0, byte[] arg1) {
        CompanyDto companyInfo=null;

        ObjectMapper objectMapper=new ObjectMapper();
        try{
            companyInfo=objectMapper.readValue(arg1, CompanyDto.class);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return companyInfo;

    }
}
