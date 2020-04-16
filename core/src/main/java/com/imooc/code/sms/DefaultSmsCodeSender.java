package com.imooc.code.sms;

import org.springframework.stereotype.Component;

@Component
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String phoneNum, String code) {
        System.out.println("向手机[" + phoneNum + "] 发送验证码:" + code);
    }
}
