package org.mindcapture.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mindcapture.DTO.LoginDTO;
import org.mindcapture.DTO.RegisterDTO;
import org.mindcapture.DTO.Result;
import org.mindcapture.Service.UserService;
import org.mindcapture.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @param captchaId 验证码ID
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody @Valid LoginDTO loginDTO, @RequestParam String captchaId) {
        return userService.login(loginDTO, captchaId);
    }

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @param captchaId 验证码ID
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody @Valid RegisterDTO registerDTO, @RequestParam String captchaId) {
        return userService.register(registerDTO, captchaId);
    }
}