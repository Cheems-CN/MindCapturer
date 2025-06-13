package org.mindcapture.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mindcapture.DTO.LoginDTO;
import org.mindcapture.DTO.RegisterDTO;
import org.mindcapture.DTO.Result;
import org.mindcapture.Entity.User;
import org.mindcapture.Mapper.UserMapper;
import org.mindcapture.Service.UserService;
import org.mindcapture.VO.UserVO;
import org.mindcapture.Utils.CaptchaUtil;
import org.mindcapture.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaUtil captchaUtil;

    @Override
    public Result<UserVO> login(LoginDTO loginDTO, String captchaId) {
        // 验证验证码
        boolean captchaValid = captchaUtil.validateCaptcha(captchaId, loginDTO.getCaptcha());
        if (!captchaValid) {
            return Result.error("验证码错误或已过期");
        }

        try {
            // 认证用户
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 获取用户信息
            User user = getUserByUsername(loginDTO.getUsername());

            // 生成JWT令牌
            String token = jwtUtil.generateToken(user);

            // 转换为UserVO
            UserVO userVO = convertToUserVO(user, token);

            return Result.success(userVO);
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }
    }

    @Override
    @Transactional
    public Result<UserVO> register(RegisterDTO registerDTO, String captchaId) {
        // 验证验证码
        boolean captchaValid = captchaUtil.validateCaptcha(captchaId, registerDTO.getCaptcha());
        if (!captchaValid) {
            return Result.error("验证码错误或已过期");
        }

        // 验证两次密码是否一致
        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getConfirmPassword())) {
            return Result.error("两次密码不一致");
        }

        // 检查用户名是否已存在
        User existUser = getUserByUsername(registerDTO.getUsername());
        if (existUser != null) {
            return Result.error("用户名已存在");
        }

        // 创建新用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setNickname(registerDTO.getNickname() != null ? registerDTO.getNickname() : registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setStatus(1); // 正常状态
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 保存用户
        save(user);

        // 生成JWT令牌
        String token = jwtUtil.generateToken(user);

        // 转换为UserVO
        UserVO userVO = convertToUserVO(user, token);

        return Result.success(userVO);
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }

    @Override
    public User getUserById(Long id) {
        return getById(id);
    }

    @Override
    public UserVO convertToUserVO(User user, String token) {
        return UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .token(token)
                .build();
    }
}