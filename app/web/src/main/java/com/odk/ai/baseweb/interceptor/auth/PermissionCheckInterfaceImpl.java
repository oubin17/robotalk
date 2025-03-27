package com.odk.ai.baseweb.interceptor.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.odk.ai.baseapi.inter.permission.RoleApi;
import com.odk.ai.baseutil.dto.permission.PermissionDTO;
import com.odk.ai.baseutil.dto.permission.UserRoleDTO;
import com.odk.ai.baseutil.response.PermissionQueryResponse;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * StpInterfaceImpl
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/26
 */
@Component
public class PermissionCheckInterfaceImpl implements StpInterface {

    private RoleApi roleApi;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        ServiceResponse<PermissionQueryResponse> response = this.roleApi.userRoles((String) loginId);
        List<PermissionDTO> permissions = response.getData().getPermissions();
        return permissions.stream().map(PermissionDTO::getPermissionCode).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        ServiceResponse<PermissionQueryResponse> response = this.roleApi.userRoles((String) loginId);
        List<UserRoleDTO> roles = response.getData().getRoles();
        return roles.stream().map(UserRoleDTO::getRoleCode).collect(Collectors.toList());
    }

    @Autowired
    public void setRoleApi(RoleApi roleApi) {
        this.roleApi = roleApi;
    }
}
