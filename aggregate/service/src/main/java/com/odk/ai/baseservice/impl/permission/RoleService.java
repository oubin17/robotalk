package com.odk.ai.baseservice.impl.permission;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.odk.ai.baseapi.inter.permission.RoleApi;
import com.odk.ai.basemanager.deal.permission.RoleManager;
import com.odk.ai.baseservice.template.AbstractApiImpl;
import com.odk.ai.baseutil.dto.permission.PermissionDTO;
import com.odk.ai.baseutil.dto.permission.UserRoleDTO;
import com.odk.ai.baseutil.entity.PermissionEntity;
import com.odk.ai.baseutil.enums.BizScene;
import com.odk.ai.baseutil.request.role.RoleAddRequest;
import com.odk.ai.baseutil.request.role.UserRoleRelaRequest;
import com.odk.ai.baseutil.response.PermissionQueryResponse;
import com.odk.ai.baseutil.userinfo.SessionContext;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * RoleService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/2/27
 */
@Service
public class RoleService extends AbstractApiImpl implements RoleApi {

    private RoleManager permissionManager;

    @Override
    public ServiceResponse<PermissionQueryResponse> userRoles(String userId) {
        return super.bizProcess(BizScene.USER_PERMISSION_QUERY, userId, new ApiCallBack<PermissionEntity, PermissionQueryResponse>() {

            @Override
            protected Object convert(Object request) {
                return Objects.requireNonNullElseGet(request, SessionContext::getLoginIdWithCheck);
            }

            @Override
            protected PermissionEntity doProcess(Object args) {
                return permissionManager.getAllPermissions((String) args);
            }

            @Override
            protected PermissionQueryResponse convertResult(PermissionEntity permissionEntity) {
                if (null == permissionEntity) {
                    return null;
                }
                PermissionQueryResponse permissionQueryResponse = new PermissionQueryResponse();
                permissionQueryResponse.setUserId(permissionEntity.getUserId());
                permissionQueryResponse.setRoles(permissionEntity.getRoles());

                Set<String> permissionIds = Sets.newHashSet();
                List<PermissionDTO> permissionDTOS = Lists.newArrayList();

                for (UserRoleDTO roleDTO : permissionEntity.getRoles()) {
                    List<PermissionDTO> permissions = roleDTO.getPermissions();
                    for (PermissionDTO permissionDTO : permissions) {
                        if (!permissionIds.contains(permissionDTO.getId())) {
                            permissionIds.add(permissionDTO.getId());
                            permissionDTOS.add(permissionDTO);
                        }
                    }
                }
                permissionQueryResponse.setPermissions(permissionDTOS);
                return permissionQueryResponse;
            }

        });
    }

    @Override
    public ServiceResponse<String> addRole(RoleAddRequest roleAddRequest) {
        return super.bizProcess(BizScene.USER_ROLE_ADD, roleAddRequest, new ApiCallBack<String, String>() {

            @Override
            protected void checkParams(Object request) {
                RoleAddRequest addRequest = (RoleAddRequest) request;
                AssertUtil.notNull(addRequest.getRoleCode(), BizErrorCode.PARAM_ILLEGAL, "roleCode不为空");
                AssertUtil.notNull(addRequest.getRoleName(), BizErrorCode.PARAM_ILLEGAL, "roleName不为空");
            }

            @Override
            protected Object convert(Object request) {
                RoleAddRequest addRequest = (RoleAddRequest) request;
                return new String[]{addRequest.getRoleCode(), addRequest.getRoleName()};
            }

            @Override
            protected String doProcess(Object args) {
                String[] args1 = (String[]) args;
                return permissionManager.addRole(args1[0], args1[1]);
            }

            @Override
            protected String convertResult(String roleId) {
                return roleId;
            }

        });
    }

    @Override
    public ServiceResponse<Boolean> deleteRole(String roleId) {
        return super.bizProcess(BizScene.USER_ROLE_ADD, roleId, new ApiCallBack<Boolean, Boolean>() {
            @Override
            protected void checkParams(Object request) {
                AssertUtil.notNull(roleId, BizErrorCode.PARAM_ILLEGAL, "roleId不为空");
            }
            @Override
            protected Boolean doProcess(Object args) {
                return permissionManager.deleteRole(roleId);
            }
            @Override
            protected Boolean convertResult(Boolean result) {
                return result;
            }
        });
    }

    @Override
    public ServiceResponse<String> addRoleRela(UserRoleRelaRequest relaRequest) {
        return super.bizProcess(BizScene.ROLE_RELA_ADD, relaRequest, new ApiCallBack<String, String>() {

            @Override
            protected void checkParams(Object request) {
                UserRoleRelaRequest roleRelaRequest = (UserRoleRelaRequest) request;
                AssertUtil.notNull(roleRelaRequest.getRoleId(), BizErrorCode.PARAM_ILLEGAL, "roleId 不为空");
                AssertUtil.notNull(roleRelaRequest.getUserId(), BizErrorCode.PARAM_ILLEGAL, "userId 不为空");
            }

            @Override
            protected String doProcess(Object args) {
                UserRoleRelaRequest roleRelaRequest = (UserRoleRelaRequest) args;
                return permissionManager.addUserRoleRela(roleRelaRequest.getRoleId(), roleRelaRequest.getUserId());
            }

            @Override
            protected String convertResult(String id) {
                return id;
            }

        });
    }


    @Autowired
    public void setPermissionManager(RoleManager roleManager) {
        this.permissionManager = roleManager;
    }
}
