package com.imooc.code;

import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;

public class ValidataCodeException extends AuthenticationException implements Serializable {

    private static final long serialVersionUID = -9214714156803592883L;

    public ValidataCodeException(String explanation) {
        super(explanation);
    }
}
