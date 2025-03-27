package com.odk.ai.baseapi.inter.user;

import com.odk.ai.baseutil.request.UserLoginRequest;
import com.odk.ai.baseutil.response.UserLoginResponse;
import com.odk.base.vo.response.ServiceResponse;

/**
 * UserLoginApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
public interface UserLoginApi {

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    ServiceResponse<UserLoginResponse> userLogin(UserLoginRequest userLoginRequest);

    /**
     * 注销登录
     *
     * @return
     */
    ServiceResponse<Boolean> userLogout();

}
