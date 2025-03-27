package com.odk.ai.basedomain.repository.permission;

import com.odk.ai.basedomain.dataobject.permission.UserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserRoleRepository
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
public interface UserRoleRepository extends JpaRepository<UserRoleDO, String> {

    @Query(value = "select * from t_user_role where id IN ( SELECT role_id FROM t_user_role_rel WHERE user_id = :userId ) AND STATUS = '0'", nativeQuery = true)
    List<UserRoleDO> findAllUserRole(@Param("userId") String userId);

    /**
     * 根据角色码查询角色
     *
     * @param roleCode
     * @return
     */
    UserRoleDO findByRoleCode(String roleCode);

}
