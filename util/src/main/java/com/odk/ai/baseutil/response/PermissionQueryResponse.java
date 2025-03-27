package com.odk.ai.baseutil.response;

import com.odk.ai.baseutil.dto.permission.PermissionDTO;
import com.odk.ai.baseutil.dto.permission.UserRoleDTO;
import lombok.Data;

import java.util.List;

/**
 * PermissionQueryResponse
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
@Data
public class PermissionQueryResponse {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色列表
     */
    private List<UserRoleDTO> roles;

    /**
     * 所有权限集合
     */
    private List<PermissionDTO> permissions;
}
