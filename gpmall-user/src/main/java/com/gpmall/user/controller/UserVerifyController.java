package com.gpmall.user.controller;

import com.gpmall.commons.result.ResponseData;
import com.gpmall.commons.result.ResponseUtil;
import com.gpmall.user.IUserRegisterService;
import com.gpmall.user.IUserVerifyService;
import com.gpmall.user.annotation.Anoymous;
import com.gpmall.user.constants.SysRetCodeConstants;
import com.gpmall.user.dto.UserVerifyRequest;
import com.gpmall.user.dto.UserVerifyResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * jerry 19-09-02
 * 用户注册激活
 */
@RestController
@RequestMapping("/user")
public class UserVerifyController {

    @Reference(timeout = 3000)
    IUserRegisterService iUserRegisterService;

    @Reference(timeout = 3000)
    IUserVerifyService iUserVerifyService;

    @Anoymous
    @GetMapping("/verify")
    public ResponseData verify(@RequestParam String uuid, @RequestParam String username) {
        if (!(StringUtils.isNotBlank(uuid) && StringUtils.isNotBlank(username))) {
            return new ResponseUtil<>().setErrorMsg("注册序号/用户名不允许为空");
        }
        UserVerifyRequest userVerifyRequest = new UserVerifyRequest();
        userVerifyRequest.setUserName(username);
        userVerifyRequest.setUuid(uuid);
        UserVerifyResponse userVerifyResponse = iUserVerifyService.verifyMember(userVerifyRequest);
        if (userVerifyResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil().setData(null);
        } else {
            return new ResponseUtil().setData(userVerifyResponse.getMsg());
        }
    }
}
