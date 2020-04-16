package com.imooc;

import com.imooc.code.image.ImageCode;
import com.imooc.code.ValidateGenerator;

import javax.servlet.http.HttpServletRequest;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateGenerator {
    @Override
    public ImageCode createImageCode(HttpServletRequest request) {
        System.out.println("我是更高级的图形验证码生成");
        System.out.println("我将要覆盖core里面默认的配置");
        return null;
    }
}
