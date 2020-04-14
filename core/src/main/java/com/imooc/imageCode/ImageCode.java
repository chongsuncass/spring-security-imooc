package com.imooc.imageCode;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Setter
@Getter
public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage bufferedImage, String code, int expireTimeIn) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(60);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
