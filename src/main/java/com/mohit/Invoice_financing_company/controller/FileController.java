package com.mohit.Invoice_financing_company.controller;

import com.mohit.Invoice_financing_company.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
@RestController
@RequestMapping("/company/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;




    @GetMapping(value = "/invoice/{fileName}")
    public void getInvoice(@PathVariable String fileName, HttpServletResponse res,@RequestHeader("X-User-Id") String companyId) throws IOException{
        InputStream ips=fileService.getInvoice( fileName,companyId);
        res.setContentType(MediaType.APPLICATION_PDF_VALUE);
        StreamUtils.copy(ips, res.getOutputStream());
    }
    @GetMapping(value = "/logo/{fileName}")
    public void getlogo(@PathVariable String fileName, HttpServletResponse res,@RequestHeader("X-User-Id") String companyId) throws IOException{
        InputStream ips=fileService.getLogo(fileName, companyId);
        res.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(ips, res.getOutputStream());
    }

}
