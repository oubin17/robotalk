package com.odk.ai.baseutil.mapper;

import com.odk.ai.baseutil.dto.user.UserRegisterDTO;
import com.odk.ai.baseutil.request.UserRegisterRequest;
import org.mapstruct.Mapper;

/**
 * UserRegisterMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    UserRegisterDTO toDTO(UserRegisterRequest userRegisterRequest);
}
