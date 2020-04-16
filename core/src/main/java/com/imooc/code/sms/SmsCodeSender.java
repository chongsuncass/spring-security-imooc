package com.imooc.code.sms;

/**
 * 短信验证码的发送接口，不同的运营商对应不同的实现
 */
public interface SmsCodeSender {
    /**
     * 发送短信验证码
     * @param phoneNum 手机号码
     * @param code 验证码
     */
    void send(String phoneNum, String code);
}
