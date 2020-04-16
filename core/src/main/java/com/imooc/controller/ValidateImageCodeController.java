package com.imooc.controller;

import com.imooc.imageCode.ImageCode;
import com.imooc.imageCode.ValidateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/image/code")
public class ValidateImageCodeController {
    public static final String SESSION_IMAGE_CODE_KEY = "session_image_code_key";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateGenerator validateGenerator;

    @GetMapping("/create")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = validateGenerator.createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_CODE_KEY, imageCode);
        try {
            ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
