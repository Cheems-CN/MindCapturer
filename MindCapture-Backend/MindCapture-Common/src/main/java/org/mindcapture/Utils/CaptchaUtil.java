package org.mindcapture.Utils;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码工具类
 */
@Component
public class CaptchaUtil {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${kaptcha.expiration}")
    private Long expiration;

    /**
     * 生成验证码
     *
     * @return 验证码ID和图片
     */
    public CaptchaResult generateCaptcha() {
        // 生成验证码文本
        String captchaText = captchaProducer.createText();
        
        // 生成验证码图片
        BufferedImage captchaImage = captchaProducer.createImage(captchaText);
        
        // 生成验证码ID
        String captchaId = UUID.randomUUID().toString();
        
        // 将验证码文本保存到Redis，设置过期时间
        String redisKey = "captcha:" + captchaId;
        redisTemplate.opsForValue().set(redisKey, captchaText, expiration, TimeUnit.SECONDS);
        
        return new CaptchaResult(captchaId, captchaImage);
    }
    
    /**
     * 验证验证码
     *
     * @param captchaId 验证码ID
     * @param captchaText 用户输入的验证码
     * @return 是否正确
     */
    public boolean validateCaptcha(String captchaId, String captchaText) {
        if (captchaId == null || captchaText == null) {
            return false;
        }
        
        String redisKey = "captcha:" + captchaId;
        String storedCaptcha = redisTemplate.opsForValue().get(redisKey);
        
        if (storedCaptcha == null) {
            return false;
        }
        
        // 验证后删除验证码
        redisTemplate.delete(redisKey);
        
        return storedCaptcha.equalsIgnoreCase(captchaText);
    }
    
    /**
     * 验证码结果类
     */
    public static class CaptchaResult {
        private String captchaId;
        private BufferedImage image;
        
        public CaptchaResult(String captchaId, BufferedImage image) {
            this.captchaId = captchaId;
            this.image = image;
        }
        
        public String getCaptchaId() {
            return captchaId;
        }
        
        public BufferedImage getImage() {
            return image;
        }
    }
}