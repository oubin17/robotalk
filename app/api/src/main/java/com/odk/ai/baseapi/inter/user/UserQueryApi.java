package com.odk.ai.baseapi.inter.user;

import com.odk.ai.baseutil.entity.UserEntity;
import com.odk.ai.baseutil.request.UserQueryRequest;
import com.odk.base.vo.response.ServiceResponse;

/**
 * UserQueryApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
public interface UserQueryApi {


    /**
     * 根据userId查询对象
     *
     * @param userId
     * @return
     */
    ServiceResponse<UserEntity> queryUserByUserId(String userId);

    /**
     * 查找当前登录用户信息
     *
     * @return
     */
    ServiceResponse<UserEntity> queryCurrentUser();


    /**
     * 根据loginId查询对象
     *
     * @param userQueryRequest
     * @return
     */
    ServiceResponse<UserEntity> queryUserByLoginId(UserQueryRequest userQueryRequest);

}
