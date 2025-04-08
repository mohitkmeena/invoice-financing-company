package com.mohit.Invoice_financing_company.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadInvoice(MultipartFile file,String companyId) ;
    InputStream getInvoice(String fileName,String companyId) ;
    String uploadLogo(MultipartFile file,String companyId) ;
    InputStream getLogo(String fileName,String companyId) ;


}