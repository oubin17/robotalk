package com.odk.ai.baseutil.mapper;

import com.odk.ai.baseutil.dto.user.UserLoginDTO;
import com.odk.ai.baseutil.entity.UserEntity;
import com.odk.ai.baseutil.request.UserLoginRequest;
import com.odk.ai.baseutil.response.UserLoginResponse;
import org.mapstruct.Mapper;

/**
 * UserLoginMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserLoginMapper {

    UserLoginDTO toDTO(UserLoginRequest userLoginRequest);

    UserLoginResponse toResponse(UserEntity userEntity);
}
