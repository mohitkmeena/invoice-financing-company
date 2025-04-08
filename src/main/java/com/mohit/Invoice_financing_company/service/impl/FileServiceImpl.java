package com.mohit.Invoice_financing_company.service.impl;

import com.mohit.Invoice_financing_company.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileServiceImpl implements FileService {

    @Value("${files.logos}")
    private String LOGO_PATH;
    @Value("${files.invoices}")
    private String INVOICES_PATH;



    public String uploadFile(String path, MultipartFile file) throws IOException {
        //get name of file
        String name = file.getOriginalFilename();
        String filePath = path + File.separator + name;
        //create a file object
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        //copy file or upload
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return name;

    }


    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filepath = path + File.separator + fileName;
        return new FileInputStream(filepath);
    }

    @Override
    public String uploadInvoice(MultipartFile file, String companyId)  {
        String name = companyId+"_invoice"+file.getOriginalFilename();
        String filePath = INVOICES_PATH + File.separator +name;

        //create a file object
        File f = new File(INVOICES_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            //copy file or upload
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return name;
        }catch (IOException e){
            return null;
        }
    }

    @Override
    public InputStream getInvoice( String fileName, String companyId)  {
        try {
            String filepath = INVOICES_PATH + File.separator + fileName;
            return new FileInputStream(filepath);
        }
        catch (FileNotFoundException e){
            return null;
        }
    }

    @Override
    public String uploadLogo(MultipartFile file, String companyId)  {
        try {
            String name = companyId + "_logo" + file.getOriginalFilename();
            String filePath = LOGO_PATH + File.separator + name;

            //create a file object
            File f = new File(LOGO_PATH);
            if (!f.exists()) {
                f.mkdirs();
            }
            //copy file or upload
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            return name;
        }
        catch (IOException e){
            return null;
        }
    }

    @Override
    public InputStream getLogo(String fileName, String companyId)  {
        try {


            String filepath = LOGO_PATH + File.separator + fileName;
            return new FileInputStream(filepath);
        }
        catch (FileNotFoundException e){
            return null;
        }

    }
}
