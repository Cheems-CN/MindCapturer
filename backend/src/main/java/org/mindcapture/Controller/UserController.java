package org.mindcapture.Controller;


import lombok.RequiredArgsConstructor;
import org.mindcapture.Pojo.Result;
import org.mindcapture.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String jwt = userService.login(username, password);
            return Result.success(jwt);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result<String> register() {
        return Result.success("");
    }
}
