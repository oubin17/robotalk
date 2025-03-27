package com.odk.ai.baseweb.user;

import com.odk.ai.baseapi.inter.user.UserRegisterApi;
import com.odk.ai.baseutil.request.UserRegisterRequest;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserRegisterController
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@RestController
@RequestMapping("/user/register")
public class UserRegisterController {

    private final UserRegisterApi userRegisterApi;

    public UserRegisterController(UserRegisterApi userRegisterApi) {
        this.userRegisterApi = userRegisterApi;
    }

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping()
    public ServiceResponse<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userRegisterApi.userRegister(userRegisterRequest);
    }
}
