package com.imooc.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码属性: 图形验证码，手机，微信
 */
@Setter
@Getter
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
