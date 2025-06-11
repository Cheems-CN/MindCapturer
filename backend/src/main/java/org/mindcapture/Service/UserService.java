package org.mindcapture.Service;

import org.mindcapture.Pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return JWT令牌
     */
    String login(String username, String password);
    
    /**
     * 获取当前登录用户信息
     * @return 用户信息
     */
    User getCurrentUser();
}
