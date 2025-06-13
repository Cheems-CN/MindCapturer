package org.mindcapture.Utils;

import com.google.code.kaptcha.Producer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mindcapture.Entity.Captcha;
import org.mindcapture.repository.CaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.UUID;

/**
 * 验证码工具类
 */
@Component
@RequiredArgsConstructor
public class CaptchaUtil {

    private final Producer captchaProducer;
    private final CaptchaRepository captchaRepository;
    private final MongoTemplate mongoTemplate;

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
        
        // 将验证码文本保存到MongoDB，设置过期时间
        Captcha captcha = new Captcha(captchaId, captchaText, expiration);
        captchaRepository.save(captcha);
        
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
        
        Optional<Captcha> captcha = captchaRepository.findById(captchaId);
        
        if (captcha.get().isExpired()) {
            return false;
        }
        
        // 验证后删除验证码
        captchaRepository.deleteById(captchaId);
        
        return captcha.get().getText().equalsIgnoreCase(captchaText);
    }
    
    /**
     * 根据ID获取验证码图片
     *
     * @param captchaId 验证码ID
     * @return 验证码图片
     */
    public BufferedImage getCaptchaImage(String captchaId) {
        if (captchaId == null) {
            return null;
        }
        
        Optional<Captcha> captcha = captchaRepository.findById(captchaId);
        
        if (captcha.get().isExpired()) {
            return null;
        }
        
        // 生成验证码图片
        return captchaProducer.createImage(captcha.get().getText());
    }
    
    /**
     * 清理过期验证码
     */
    public void cleanExpiredCaptchas() {
        Query query = new Query();
        query.addCriteria(Criteria.where("expireTime").lt(java.time.LocalDateTime.now()));
        mongoTemplate.remove(query, Captcha.class);
    }
    
    /**
     * 验证码结果类
     */
    @Getter
    public static class CaptchaResult {
        private final String captchaId;
        private final BufferedImage image;
        
        public CaptchaResult(String captchaId, BufferedImage image) {
            this.captchaId = captchaId;
            this.image = image;
        }

    }
}