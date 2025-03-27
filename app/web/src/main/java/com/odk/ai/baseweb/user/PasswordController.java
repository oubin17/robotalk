package com.odk.ai.baseweb.user;

import com.odk.ai.baseapi.inter.user.PasswordApi;
import com.odk.ai.baseutil.request.password.PasswordUpdateRequest;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PasswordController
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/1/17
 */
@CrossOrigin
@RestController
@RequestMapping("/user/password")
public class PasswordController {

    private PasswordApi passwordApi;

    /**
     * 更新密码
     *
     */
    @PostMapping("/update")
    ServiceResponse<Boolean> updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest) {
        return passwordApi.passwordUpdate(passwordUpdateRequest);
    }

    @Autowired
    public void setPasswordApi(PasswordApi passwordApi) {
        this.passwordApi = passwordApi;
    }
}
