package com.odk.ai.basedomain.dataobject.permission;

import com.odk.base.dos.BaseDO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

/**
 * UserRoleRelDO
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_user_role_rel", indexes = {
        @Index(name = "idx_user_role_id", columnList = "user_id,role_id", unique = true)
})
@EntityListeners(AuditingEntityListener.class)
public class UserRoleRelDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 2261404806508249134L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private String roleId;

}
