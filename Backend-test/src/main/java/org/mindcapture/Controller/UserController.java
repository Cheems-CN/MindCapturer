package org.mindcapture.Controller;

import lombok.RequiredArgsConstructor;
import org.mindcapture.Pojo.User;
import org.mindcapture.Service.UserService;
import org.mindcapture.Utils.JWTUtil;
import org.mindcapture.Utils.Md5Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.mindcapture.Pojo.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Result<String> register(){

    }

    @PostMapping("/login")
    public Result<String> login(String phone, String password){
        User u = userService.findByPhone(phone);
        if(u == null){
          return Result.error("用户不存在");
        } else if(!password.equals(Md5Util.getMD5String(password))){
            return Result.error("密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", u.id);
        claims.put("name", u.name);
        String jwt = JWTUtil.getJWT(claims);
        return Result.success(jwt);

    }

    @

}
