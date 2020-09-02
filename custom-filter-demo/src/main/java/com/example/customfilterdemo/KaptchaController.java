package com.example.customfilterdemo;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author hanliukui
 * @Date 2020/9/2 17:39
 */
@Controller
@RequestMapping
public class KaptchaController {

    /**
     * 验证码工具
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha.jpg")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

            // 设置内容类型
            response.setContentType("image/jpeg");
            // 创建验证码文本
            String createText = defaultKaptcha.createText();
            // 将生成的验证码保存在session中
            request.getSession().setAttribute("kaptcha", createText);
            // 创建验证码图片
            BufferedImage bi = defaultKaptcha.createImage(createText);

            // 获取响应输出流
            ServletOutputStream out = response.getOutputStream();
            // 将图片验证码数据写入到图片输出流
            ImageIO.write(bi, "jpg", out);

            // 推送并关闭输出流
            out.flush();
            out.close();
        }

}
