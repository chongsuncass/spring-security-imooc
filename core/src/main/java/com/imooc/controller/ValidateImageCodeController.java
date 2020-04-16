package com.imooc.controller;

import com.imooc.code.ValidateGenerator;
import com.imooc.code.image.ImageCode;
import com.imooc.code.sms.SmsCode;
import com.imooc.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/code")
public class ValidateImageCodeController {
    public static final String SESSION_IMAGE_CODE_KEY = "session_image_code_key";
    public static final String SESSION_SMS_CODE_KEY = "session_sms_code_key";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Resource(name = "imageCodeGenerator")
    private ValidateGenerator imageValidateGenerator;
    @Resource(name = "smsCodeGenerator")
    private ValidateGenerator smsValidateGenerator;
    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 生成图形验证码
     * @param request
     * @param response
     */
    @GetMapping("image/create")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = ((ImageCode) imageValidateGenerator.createImageCode(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_CODE_KEY, imageCode);
        try {
            ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成短信验证码
     */
    @GetMapping("sms/create")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        SmsCode smsCode = ((SmsCode) smsValidateGenerator.createImageCode(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_CODE_KEY, smsCode);

        // 模拟短信服务商发送短信
        String phoneNum = ServletRequestUtils.getStringParameter(request, "phoneNum");
        smsCodeSender.send(phoneNum,smsCode.getCode());
    }
}
