package com.imooc.code.image;

import com.imooc.code.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Setter
@Getter
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireTimeIn) {
        super(code, expireTimeIn);
        this.bufferedImage = bufferedImage;
    }
}
