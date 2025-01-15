package com.qiufen.xiaohongshu.auth.service;

import com.qiufen.framework.common.response.Response;
import com.qiufen.xiaohongshu.auth.model.vo.veriticationcode.SendVerificationCodeReqVO;

public interface VerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
