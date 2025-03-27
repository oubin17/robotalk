package com.odk.ai.baseapi.inter.robotalk;

import com.odk.ai.baseutil.request.role.RobotalkAskRequest;
import com.odk.base.vo.response.ServiceResponse;

/**
 * RobotalkApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
public interface RobotalkApi {

    /**
     * 文本输入
     *
     * @param robotalkAskRequest
     * @return
     */
    ServiceResponse<String> callAi(RobotalkAskRequest robotalkAskRequest);
}
