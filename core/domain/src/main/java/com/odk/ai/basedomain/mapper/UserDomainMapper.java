package com.odk.ai.basedomain.mapper;

import com.odk.ai.basedomain.dataobject.permission.UserRoleDO;
import com.odk.ai.basedomain.dataobject.user.UserAccessTokenDO;
import com.odk.ai.basedomain.dataobject.user.UserBaseDO;
import com.odk.ai.basedomain.dataobject.user.UserProfileDO;
import com.odk.ai.baseutil.dto.permission.UserRoleDTO;
import com.odk.ai.baseutil.entity.AccessTokenEntity;
import com.odk.ai.baseutil.entity.UserEntity;
import com.odk.ai.baseutil.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * UserDomainMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserDomainMapper {

    @Mapping(source = "id", target = "userId")
    UserEntity toEntity(UserBaseDO baseDO);

    AccessTokenEntity toEntity(UserAccessTokenDO userAccessTokenDO);

    UserProfileEntity toEntity(UserProfileDO userProfileDO);

    UserRoleDTO toDTO(UserRoleDO userRoleDO);
}
