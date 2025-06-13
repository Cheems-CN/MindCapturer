package org.mindcapture.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * MongoDB配置类
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.mindcapture.repository")
public class MongoConfig {

    /**
     * 配置MongoDB验证监听器
     *
     * @param validator 验证器工厂Bean
     * @return MongoDB事件监听器
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean validator) {
        return new ValidatingMongoEventListener(validator);
    }

    /**
     * 配置验证器工厂Bean
     *
     * @return 本地验证器工厂Bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}