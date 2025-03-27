package com.odk.ai.baseutil.request.role;

import com.odk.base.vo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * RobotalkAskRequest
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RobotalkAskRequest extends BaseRequest {

    @Serial
    private static final long serialVersionUID = 5403678779519329620L;

    /**
     * 内容
     */
    private String content;
}
