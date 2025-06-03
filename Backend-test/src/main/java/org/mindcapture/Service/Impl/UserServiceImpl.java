package org.mindcapture.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.mindcapture.Mapper.UserMapper;
import org.mindcapture.Pojo.User;
import org.mindcapture.Service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }
}
