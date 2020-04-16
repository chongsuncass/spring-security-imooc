package com.imooc.imageCode;

import javax.servlet.http.HttpServletRequest;

public interface ValidateGenerator {

   ImageCode createImageCode(HttpServletRequest request);
}
