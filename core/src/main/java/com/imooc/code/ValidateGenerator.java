package com.imooc.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateGenerator {

   ValidateCode createImageCode(HttpServletRequest request);
}
