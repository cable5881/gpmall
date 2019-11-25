package com.gpmall.user.services;

import com.gpmall.user.IUserVerifyService;
import com.gpmall.user.constants.SysRetCodeConstants;
import com.gpmall.user.dal.entitys.Member;
import com.gpmall.user.dal.entitys.UserVerify;
import com.gpmall.user.dal.persistence.MemberMapper;
import com.gpmall.user.dal.persistence.UserVerifyMapper;
import com.gpmall.user.dto.UserVerifyRequest;
import com.gpmall.user.dto.UserVerifyResponse;
import com.gpmall.user.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Administrator
 * 2019/9/2 0002
 * 14:45
 */
@Service
@Slf4j
public class UserVerifyServiceImpl implements IUserVerifyService {

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    UserVerifyMapper userVerifyMapper;

    @Override
    public UserVerifyResponse verifyMember(UserVerifyRequest request) {
        UserVerifyResponse response = new UserVerifyResponse();
        try {
            request.requestCheck();

            //查询是否有该用户
            Example example = new Example(Member.class);
            example.createCriteria()
                    .andEqualTo("state", 1)
                    .andEqualTo("username", request.getUserName());
            List<Member> members = memberMapper.selectByExample(example);
            if (members == null || members.size() == 0) {
                response.setCode(SysRetCodeConstants.USER_INFOR_INVALID.getCode());
                response.setMsg(SysRetCodeConstants.USER_INFOR_INVALID.getMessage());
                return response;
            }
            Member member = members.get(0);

            //是否存在注册激活信息
            example.clear();
            example = new Example(UserVerify.class);
            example.createCriteria()
                    .andEqualTo("uuid", request.getUuid());
            List<UserVerify> userVerifys = userVerifyMapper.selectByExample(example);
            if (userVerifys == null || userVerifys.size() == 0) {
                response.setCode(SysRetCodeConstants.USERVERIFY_INFOR_INVALID.getCode());
                response.setMsg(SysRetCodeConstants.USERVERIFY_INFOR_INVALID.getMessage());
                return response;
            }

            example.clear();
            example.createCriteria()
                    .andEqualTo("uuid", request.getUuid());
            UserVerify userVerify = userVerifys.get(0);
            userVerify.setIsVerify("Y");
            //激活用户，修改tb_user_verify的信息 is_verify
            userVerifyMapper.updateByExample(userVerify, example);

            //更新Member表的is_verify
            example.clear();
            example = new Example(Member.class);
            Member updateMb = new Member();
            updateMb.setId(member.getId());
            updateMb.setIsVerified("Y");
            memberMapper.updateByExampleSelective(updateMb, example);

            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setCode(SysRetCodeConstants.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("active user error", e);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }

        return response;
    }

}
