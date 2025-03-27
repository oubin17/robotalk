package com.odk.ai.basemanager.deal.permission;

import com.odk.ai.basedomain.dataobject.permission.UserRoleDO;
import com.odk.ai.basedomain.dataobject.permission.UserRoleRelDO;
import com.odk.ai.basedomain.domain.PermissionDomain;
import com.odk.ai.basedomain.domain.UserQueryDomain;
import com.odk.ai.basedomain.repository.permission.UserRoleRelRepository;
import com.odk.ai.basedomain.repository.permission.UserRoleRepository;
import com.odk.ai.baseutil.entity.PermissionEntity;
import com.odk.base.enums.common.CommonStatusEnum;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PermissionManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
@Slf4j
@Service
public class RoleManager {

    private UserRoleRepository userRoleRepository;

    private UserRoleRelRepository relRepository;

    private PermissionDomain permissionDomain;

    private UserQueryDomain userQueryDomain;
    /**
     * 查找用户权限
     *
     * @param userId
     * @return
     */
    public PermissionEntity getAllPermissions(String userId) {
        return permissionDomain.getPermissionByUserId(userId);
    }

    /**
     * 添加角色
     *
     * @param roleCode
     * @param roleName
     * @return
     */
    public String addRole(String roleCode, String roleName) {
        UserRoleDO userRoleDO = userRoleRepository.findByRoleCode(roleCode);
        AssertUtil.isNull(userRoleDO, BizErrorCode.PARAM_ILLEGAL, "角色码重复，添加角色失败");
        UserRoleDO addRole = new UserRoleDO();
        addRole.setRoleCode(roleCode);
        addRole.setRoleName(roleName);
        addRole.setStatus(CommonStatusEnum.NORMAL.getCode());
        UserRoleDO save = userRoleRepository.save(addRole);
        return save.getId();
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    public Boolean deleteRole(String roleId) {
        Optional<UserRoleDO> userRoleDO = userRoleRepository.findById(roleId);
        AssertUtil.isTrue(userRoleDO.isPresent(), BizErrorCode.PARAM_ILLEGAL, "角色不存在");
        UserRoleDO updateUserRoleDO = userRoleDO.get();
        updateUserRoleDO.setStatus(CommonStatusEnum.DELETE.getCode());
        userRoleRepository.save(userRoleDO.get());
        return true;
    }

    public String addUserRoleRela(String roleId, String userId) {
        UserRoleRelDO userRoleRelDO = relRepository.findByUserIdAndRoleId(userId, roleId);
        AssertUtil.isNull(userRoleRelDO, BizErrorCode.PARAM_ILLEGAL, "用户已具备该权限");

        Optional<UserRoleDO> userRoleDO = userRoleRepository.findById(roleId);
        AssertUtil.isTrue(userRoleDO.isPresent(), BizErrorCode.PARAM_ILLEGAL, "角色不存在");
        this.userQueryDomain.queryByUserIdAndCheck(userId);
        UserRoleRelDO roleRelDO = new UserRoleRelDO();
        roleRelDO.setUserId(userId);
        roleRelDO.setRoleId(roleId);
        UserRoleRelDO save = relRepository.save(roleRelDO);
        return save.getId();
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    public void setRelRepository(UserRoleRelRepository relRepository) {
        this.relRepository = relRepository;
    }

    @Autowired
    public void setPermissionDomain(PermissionDomain permissionDomain) {
        this.permissionDomain = permissionDomain;
    }

    @Autowired
    public void setUserQueryDomain(UserQueryDomain userQueryDomain) {
        this.userQueryDomain = userQueryDomain;
    }
}
