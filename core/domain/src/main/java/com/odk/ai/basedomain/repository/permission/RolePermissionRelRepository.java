package com.odk.ai.basedomain.repository.permission;

import com.odk.ai.basedomain.dataobject.permission.RolePermissionRelDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RolePermissionRelRepository
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
public interface RolePermissionRelRepository extends JpaRepository<RolePermissionRelDO, String> {
}
