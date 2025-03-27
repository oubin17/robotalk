package com.odk.ai.basedomain.domain;

import com.odk.ai.baseutil.dto.user.PasswordResetDTO;
import com.odk.ai.baseutil.dto.user.PasswordUpdateDTO;
import com.odk.ai.baseutil.dto.user.UserLoginDTO;
import com.odk.ai.baseutil.dto.user.UserRegisterDTO;
import com.odk.ai.baseutil.entity.UserEntity;

/**
 * UserDomain
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/29
 */
public interface UserDomain {

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     * @return
     */
    String registerUser(UserRegisterDTO userRegisterDTO);

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    UserEntity userLogin(UserLoginDTO userLoginDTO);

    /**
     * 更新密码
     * @param passwordUpdateDTO
     * @return
     */
    boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO);

    /**
     * 重置密码
     *
     * @param resetDTO
     * @return
     */
    boolean resetPassword(PasswordResetDTO resetDTO);

}
