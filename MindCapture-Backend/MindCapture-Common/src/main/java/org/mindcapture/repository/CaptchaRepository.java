package org.mindcapture.repository;

import org.mindcapture.Entity.Captcha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 验证码存储库
 */
@Repository
public interface CaptchaRepository extends MongoRepository<Captcha, String> {
    
    /**
     * 根据ID查找验证码
     *
     * @param id 验证码ID
     * @return 验证码实体
     */
    Optional<Captcha> findById(String id);
    
    /**
     * 删除验证码
     *
     * @param id 验证码ID
     */
    void deleteById(String id);
}