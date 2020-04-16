package com.imooc.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 图形验证码的配置
 */
@Setter
@Getter
public class SmsCodeProperties {
    /**
     * 图形验证码的有效时间
     */
    private int expireIn = 60;
    /**
     * 图形验证码的长度
     */
    private int length = 6;
}
