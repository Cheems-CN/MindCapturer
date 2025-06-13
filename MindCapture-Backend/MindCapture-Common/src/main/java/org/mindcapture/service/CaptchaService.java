package org.mindcapture.service;

import org.mindcapture.Entity.Captcha;
import org.mindcapture.repository.CaptchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 验证码服务类
 */
@Service
public class CaptchaService {

    @Autowired
    private CaptchaRepository captchaRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * 定时清理过期验证码
     * 每小时执行一次
     */
    @Scheduled(fixedRate = 3600000)
    public void cleanExpiredCaptchas() {
        Query query = new Query();
        query.addCriteria(Criteria.where("expireTime").lt(LocalDateTime.now()));
        mongoTemplate.remove(query, Captcha.class);
    }
}