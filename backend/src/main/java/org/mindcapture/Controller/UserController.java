package org.mindcapture.Controller;


import lombok.RequiredArgsConstructor;
import org.mindcapture.Pojo.Result;
import org.mindcapture.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(){
        return Result.success("");
    }

    @PostMapping("/register")
    public Result<String> register(){
        return Result.success("");
    }
}
