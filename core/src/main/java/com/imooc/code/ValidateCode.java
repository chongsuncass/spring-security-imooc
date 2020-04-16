package com.imooc.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ValidateCode {

    public ValidateCode() {
    }

    public ValidateCode(String code, int expireTimeIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTimeIn);
    }

    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
