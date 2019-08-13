package com.example.demo.web;

import com.google.code.kaptcha.Producer;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class KaptchaController {
    @Autowired
    private Producer producer;
    @PostMapping("/getCode")
    public void getCode(HttpServletResponse response){
        response.setHeader("Cache-Control","no-store, no-cache");
        response.setContentType("image/jpeg");
        try {
            String code = producer.createText();
            BufferedImage image = producer.createImage(code);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image,"jpg",out);
            //关闭IO
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
