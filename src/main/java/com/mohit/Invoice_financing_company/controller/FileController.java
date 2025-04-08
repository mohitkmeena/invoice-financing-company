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

    @Value("${project.poster}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException {

       // String uploadedFileName= fileService.uploadFile(path, file);
       // return ResponseEntity.ok("file uplaoded "+uploadedFileName);
        return null;

    }
    @GetMapping(value = "/{fileName}")
    public void getFile(@PathVariable String fileName, HttpServletResponse res) throws IOException{
//        InputStream ips=fileService.getResource(path, fileName);
//        res.setContentType(MediaType.IMAGE_PNG_VALUE);
//        StreamUtils.copy(ips, res.getOutputStream());
    }

}
