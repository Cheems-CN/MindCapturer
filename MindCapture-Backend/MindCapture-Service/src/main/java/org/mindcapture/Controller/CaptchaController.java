package org.mindcapture.Controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.mindcapture.DTO.Result;
import org.mindcapture.Utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码控制器
 */
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaUtil captchaUtil;

    /**
     * 生成验证码
     *
     * @param response HTTP响应
     * @return 验证码ID
     */
    @GetMapping("/generate")
    public Result<Map<String, String>> generateCaptcha(HttpServletResponse response) {
        // 生成验证码
        CaptchaUtil.CaptchaResult captchaResult = captchaUtil.generateCaptcha();
        
        // 设置响应类型
        response.setContentType("image/jpeg");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            // 输出验证码图片
            ImageIO.write(captchaResult.getImage(), "jpg", outputStream);
            outputStream.flush();
            
            // 返回验证码ID
            Map<String, String> result = new HashMap<>();
            result.put("captchaId", captchaResult.getCaptchaId());
            return Result.success(result);
        } catch (IOException e) {
            return Result.error("生成验证码失败");
        }
    }
}