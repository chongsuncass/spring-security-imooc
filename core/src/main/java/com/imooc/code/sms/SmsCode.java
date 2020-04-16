package com.imooc.code.sms;

import com.imooc.code.ValidateCode;

public class SmsCode extends ValidateCode {
    public SmsCode() {
    }

    public SmsCode(String code, int expireTimeIn) {
        super(code, expireTimeIn);
    }
}
