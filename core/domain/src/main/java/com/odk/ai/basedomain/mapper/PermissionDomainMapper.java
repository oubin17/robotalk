package com.odk.ai.basedomain.mapper;

import com.odk.ai.basedomain.dataobject.permission.PermissionDO;
import com.odk.ai.baseutil.dto.permission.PermissionDTO;
import org.mapstruct.Mapper;

/**
 * PermissionDomainMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface PermissionDomainMapper {

    PermissionDTO toDTO(PermissionDO permissionDO);
}
