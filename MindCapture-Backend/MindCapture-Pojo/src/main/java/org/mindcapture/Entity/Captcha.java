package org.mindcapture.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 验证码实体类
 */
@Data
@Document(collection = "captcha")
public class Captcha {
    
    @Id
    private String id;
    
    /**
     * 验证码文本
     */
    private String text;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    
    public Captcha() {
    }
    
    public Captcha(String id, String text, long expirationInSeconds) {
        this.id = id;
        this.text = text;
        this.createTime = LocalDateTime.now();
        this.expireTime = this.createTime.plusSeconds(expirationInSeconds);
    }
    
    /**
     * 检查验证码是否过期
     *
     * @return 是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}