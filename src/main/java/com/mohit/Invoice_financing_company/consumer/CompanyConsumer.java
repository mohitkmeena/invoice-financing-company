package com.mohit.Invoice_financing_company.consumer;

import com.mohit.Invoice_financing_company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CompanyConsumer {

    @KafkaListener(topics = "${spring.kafka.company-topic-name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CompanyDto companyDto){

      //  System.out.println("Message received from investor topic: "+message);
        System.out.println("Company received from investor topic: "+companyDto);
    }

}
