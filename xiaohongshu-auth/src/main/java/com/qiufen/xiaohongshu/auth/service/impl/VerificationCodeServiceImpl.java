package com.qiufen.xiaohongshu.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.qiufen.framework.common.exception.BizException;
import com.qiufen.framework.common.response.Response;
import com.qiufen.xiaohongshu.auth.constant.RedisKeyConstants;
import com.qiufen.xiaohongshu.auth.enums.ResponseCodeEnum;
import com.qiufen.xiaohongshu.auth.model.vo.veriticationcode.SendVerificationCodeReqVO;
import com.qiufen.xiaohongshu.auth.service.VerificationCodeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        String phone = sendVerificationCodeReqVO.getPhone();
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        Boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            // 若之前发送的验证码未过期，则提示发送频繁
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
        }
        String verificationCode = RandomUtil.randomNumbers(6);
        // todo: 调用第三方短信发送服务
        log.info("==> 手机号: {}, 已发送验证码：【{}】", phone, verificationCode);
        // 存储验证码到 redis, 并设置过期时间为 3 分钟
        redisTemplate.opsForValue().set(key, verificationCode, 3, TimeUnit.MINUTES);
        return Response.success();
    }
}
