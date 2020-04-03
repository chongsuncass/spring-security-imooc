package com.imooc.controller;

import com.imooc.model.FileInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "C:/code/spring-security-mine/demo";

    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(folder, new Date().getTime() + ".png");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/download/{id}")
    public String download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("id:" + id);
        try (FileInputStream fileInputStream = new FileInputStream(new File(folder, id + ".png"));
             OutputStream out = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Dispostion", "attachment;filenam=text.png");
            IOUtils.copy(fileInputStream, out);
            out.flush();
        } catch (Exception e) {
           return "download failed";
        }
        return "download successfully";
    }
}
