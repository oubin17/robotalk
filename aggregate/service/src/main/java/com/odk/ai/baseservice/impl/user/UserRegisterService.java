package com.odk.ai.baseservice.impl.user;

import com.odk.ai.baseapi.inter.user.UserRegisterApi;
import com.odk.ai.basemanager.deal.user.UserRegisterManager;
import com.odk.ai.basemanager.deal.verificationcode.SmsVerificationManager;
import com.odk.ai.baseservice.template.AbstractApiImpl;
import com.odk.ai.baseutil.dto.user.UserRegisterDTO;
import com.odk.ai.baseutil.enums.BizScene;
import com.odk.ai.baseutil.mapper.UserRegisterMapper;
import com.odk.ai.baseutil.request.UserRegisterRequest;
import com.odk.base.enums.user.IdentificationTypeEnum;
import com.odk.base.enums.user.TokenTypeEnum;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import com.odk.base.vo.request.BaseRequest;
import com.odk.base.vo.response.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserRegisterService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@Service
public class UserRegisterService extends AbstractApiImpl implements UserRegisterApi {

    private SmsVerificationManager smsVerificationManager;

    private UserRegisterManager userRegisterManager;

    private UserRegisterMapper userRegisterMapper;

    @Override
    public ServiceResponse<String> userRegister(UserRegisterRequest userRegisterRequest) {
        return super.strictBizProcess(BizScene.USER_REGISTER, userRegisterRequest, new StrictApiCallBack<String, String>() {

            @Override
            protected void checkParams(BaseRequest request) {
                super.checkParams(request);
                UserRegisterRequest registerRequest = (UserRegisterRequest) request;
                AssertUtil.notNull(registerRequest.getLoginId(), BizErrorCode.PARAM_ILLEGAL, "loginId is null.");
                AssertUtil.notNull(TokenTypeEnum.getByCode(registerRequest.getLoginType()), BizErrorCode.PARAM_ILLEGAL, "loginType is null.");
                AssertUtil.notNull(IdentificationTypeEnum.getByCode(registerRequest.getIdentifyType()), BizErrorCode.PARAM_ILLEGAL, "identifyType is null.");
                AssertUtil.notNull(registerRequest.getIdentifyValue(), BizErrorCode.PARAM_ILLEGAL, "password is null.");
                AssertUtil.isTrue(StringUtils.isNotEmpty(registerRequest.getUserName()), BizErrorCode.PARAM_ILLEGAL, "userName is null.");
            }

            @Override
            protected void beforeProcess(BaseRequest request) {
                //检查验证码是否有效
                UserRegisterRequest registerRequest = (UserRegisterRequest) request;
                AssertUtil.isTrue(smsVerificationManager.verifySms(registerRequest.getVerificationCode()), BizErrorCode.VERIFY_CODE_UNMATCHED, "验证码不匹配，请重新输入");
            }

            @Override
            protected Object convert(BaseRequest request) {
                UserRegisterRequest registerRequest = (UserRegisterRequest) request;
                return userRegisterMapper.toDTO(registerRequest);
            }

            @Override
            protected String doProcess(Object args) {
                UserRegisterDTO userRegisterDTO = (UserRegisterDTO) args;
                return userRegisterManager.registerUser(userRegisterDTO);
            }

            @Override
            protected String convertResult(String apiResult) {
                return apiResult;
            }
        });
    }

    @Autowired
    public void setUserRegisterManager(UserRegisterManager userRegisterManager) {
        this.userRegisterManager = userRegisterManager;
    }

    @Autowired
    public void setSmsVerificationManager(SmsVerificationManager smsVerificationManager) {
        this.smsVerificationManager = smsVerificationManager;
    }

    @Autowired
    public void setUserRegisterMapper(UserRegisterMapper userRegisterMapper) {
        this.userRegisterMapper = userRegisterMapper;
    }
}
