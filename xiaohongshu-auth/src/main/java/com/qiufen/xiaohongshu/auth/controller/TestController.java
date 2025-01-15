package com.qiufen.xiaohongshu.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.qiufen.framework.biz.operationlog.aspect.ApiOperationLog;
import com.qiufen.framework.common.response.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperationLog
    public Response<String> test() {
        return Response.success("Hello, qf");
    }

    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test2() {
        return Response.success(User.builder()
                .nickName("秋分")
                .createTime(LocalDateTime.now())
                .build());
    }

    @PostMapping("/test3")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test3(@RequestBody @Validated User user) {
        int i = 1 / 0;
        return Response.success(user);
    }

    // 测试登录，浏览器访问： http://localhost:8080/user/doLogin?username=zhang&password=123456
    @RequestMapping("/user/doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("qiufen".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("/user/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
