package com.odk.ai.baseweb.permission;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.odk.ai.baseapi.inter.permission.PermissionApi;
import com.odk.ai.baseutil.request.role.PermissionAddRequest;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PermissionController
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/8
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionApi permissionApi;

    public PermissionController(PermissionApi permissionApi) {
        this.permissionApi = permissionApi;
    }

    /**
     * 添加权限
     *
     * @param permissionAddRequest
     * @return
     */
    @SaCheckRole(value = {"ADMIN"})
    @PostMapping("/add")
    public ServiceResponse<Boolean> addPermission(@RequestBody PermissionAddRequest permissionAddRequest) {
        return this.permissionApi.addPermission(permissionAddRequest);
    }


}
