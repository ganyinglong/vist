package com.gyl.visit.user.facade;

import com.gyl.visit.user.api.UserApi;
import com.gyl.visit.usr.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/user/info/test")
    public void test() {

    }
}
