package com.imooc.controller;

import com.imooc.imageCode.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@RestController
@RequestMapping("/image/code")
public class ValidateImageCodeController {
    public static final String SESSION_IMAGE_CODE_KEY = "session_image_code_key";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @PostMapping("/create")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_IMAGE_CODE_KEY, imageCode);
        try {
            ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建图形码对象
     * @param request
     * @return
     */
    private ImageCode createImageCode(HttpServletRequest request) {
        // 在内存中创建图象
        int width = 65, height = 20;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(230, 255));
        g.fillRect(0, 0, 100, 25);
        // 设定字体
        g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18));
        // 产生0条干扰线，
        g.drawLine(0, 0, 0, 0);
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(getRandColor(100, 150));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 15 * i + 6, 16);
        }
        for(int i=0;i<(random.nextInt(5)+5);i++){
            g.setColor(new Color(random.nextInt(255)+1,random.nextInt(255)+1,random.nextInt(255)+1));
            g.drawLine(random.nextInt(100),random.nextInt(30),random.nextInt(100),random.nextInt(30));
        }
        // 图象生效
        g.dispose();

        ImageCode imageCode = new ImageCode(image, sRand, 60);
        return imageCode;
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


}
