package com.odk.ai.baseapi.inter.user;

import com.odk.ai.baseutil.request.password.PasswordUpdateRequest;
import com.odk.base.vo.response.ServiceResponse;

/**
 * PasswordApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/7
 */
public interface PasswordApi {

    /**
     * 更新密码
     *
     * @param passwordUpdateRequest
     * @return
     */
    ServiceResponse<Boolean> passwordUpdate(PasswordUpdateRequest passwordUpdateRequest);

}
