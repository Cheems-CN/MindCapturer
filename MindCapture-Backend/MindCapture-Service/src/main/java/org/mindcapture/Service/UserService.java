package org.mindcapture.Service;

import org.mindcapture.DTO.LoginDTO;
import org.mindcapture.DTO.RegisterDTO;
import org.mindcapture.DTO.Result;
import org.mindcapture.Entity.User;
import org.mindcapture.VO.UserVO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @param captchaId 验证码ID
     * @return 登录结果
     */
    Result<UserVO> login(LoginDTO loginDTO, String captchaId);

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @param captchaId 验证码ID
     * @return 注册结果
     */
    Result<UserVO> register(RegisterDTO registerDTO, String captchaId);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUsername(String username);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 转换为UserVO
     *
     * @param user 用户实体
     * @param token JWT令牌
     * @return 用户VO
     */
    UserVO convertToUserVO(User user, String token);
}