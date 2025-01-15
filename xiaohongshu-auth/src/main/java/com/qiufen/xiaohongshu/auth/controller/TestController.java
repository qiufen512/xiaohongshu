package com.qiufen.xiaohongshu.auth.controller;

import com.qiufen.framework.biz.operationlog.aspect.ApiOperationLog;
import com.qiufen.framework.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Response<User> test3(@RequestBody User user) {
        return Response.success(user);
    }
}
