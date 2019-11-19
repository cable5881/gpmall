package com.gpmall.user.controller;

import com.gpmall.commons.result.ResponseData;
import com.gpmall.commons.result.ResponseUtil;
import com.gpmall.commons.tool.utils.CookieUtil;
import com.gpmall.user.IKaptchaService;
import com.gpmall.user.IUserRegisterService;
import com.gpmall.user.annotation.Anoymous;
import com.gpmall.user.constants.SysRetCodeConstants;
import com.gpmall.user.dto.KaptchaCodeRequest;
import com.gpmall.user.dto.KaptchaCodeResponse;
import com.gpmall.user.dto.UserRegisterRequest;
import com.gpmall.user.dto.UserRegisterResponse;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/23-12:43
 */
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Reference(timeout = 3000)
    IUserRegisterService iUserRegisterService;

    @Reference(timeout = 3000)
    IKaptchaService kaptchaService;

    /**
     * 验证码开关
     */
    @Value("${captchaFlag:true}")
    private boolean captchaFlag;

    @Anoymous
    @PostMapping("/register")
    public ResponseData register(@RequestBody Map<String, String> map, HttpServletRequest request) {
        if (captchaFlag) {
            KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();
            String captcha = map.get("captcha");
            String uuid = CookieUtil.getCookieValue(request, "kaptcha_uuid");
            kaptchaCodeRequest.setUuid(uuid);
            kaptchaCodeRequest.setCode(captcha);
            KaptchaCodeResponse response = kaptchaService.validateKaptchaCode(kaptchaCodeRequest);
            if (!response.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                return new ResponseUtil<>().setErrorMsg(response.getMsg());
            }
        }

        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUserName(map.get("userName"));
        registerRequest.setUserPwd(map.get("userPwd"));
        registerRequest.setEmail(map.get("email"));
        UserRegisterResponse registerResponse = iUserRegisterService.register(registerRequest);

        if (registerResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil().setData(null);
        }
        return new ResponseUtil().setErrorMsg(registerResponse.getMsg());
    }
}
