package com.imooc.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 图形验证码的配置
 */
@Setter
@Getter
public class ImageCodeProperties {
    /**
     * 图形验证码的高度
     */
    private int height = 23;
    /**
     * 图形验证码的宽度
     */
    private int width = 67;
    /**
     * 图形验证码的有效时间
     */
    private int expireIn = 60;
    /**
     * 图形验证码的长度
     */
    private int length = 4;
    /**
     * 拦截进行图形验证的url
     */
    private String urls;
}
