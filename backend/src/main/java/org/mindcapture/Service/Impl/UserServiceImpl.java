package org.mindcapture.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.mindcapture.Mapper.UserMapper;
import org.mindcapture.Pojo.User;
import org.mindcapture.Service.UserService;
import org.mindcapture.Utils.Md5Util;
import org.mindcapture.Utils.JWTUtils;
import org.mindcapture.Utils.ThreadLocalUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        // 1. 根据用户名查询用户
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 2. 校验密码
        if (!Md5Util.checkPassword(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 3. 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        
        return JWTUtils.getJWT(claims);
    }

    @Override
    public User getCurrentUser() {
        Map<String, Object> userInfo = ThreadLocalUtils.get();
        if (userInfo == null) {
            throw new RuntimeException("用户未登录");
        }
        
        Integer userId = (Integer) userInfo.get("id");
        // 这里应该根据userId查询完整的用户信息
        // 为简化示例，这里直接返回ThreadLocal中的信息
        
        User user = new User();
        user.setId(userId);
        user.setUsername((String) userInfo.get("username"));
        return user;
    }
}
