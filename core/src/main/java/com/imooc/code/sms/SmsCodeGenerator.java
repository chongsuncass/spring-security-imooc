package com.imooc.code.sms;

import com.imooc.code.ValidateGenerator;
import com.imooc.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public SmsCode createImageCode(HttpServletRequest request) {
        String random = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsCode(random, securityProperties.getCode().getSms().getExpireIn());
    }

}


