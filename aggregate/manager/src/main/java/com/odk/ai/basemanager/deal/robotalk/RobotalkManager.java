package com.odk.ai.basemanager.deal.robotalk;

import com.odk.ai.infra.api.TurboApi;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RobotalkManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
@Slf4j
@Service
public class RobotalkManager {

    private static final String DEFAULT_RESPONSE = "对不起，暂时无法提供服务...";

    private final TurboApi turboApi;

    public RobotalkManager(TurboApi turboApi) {
        this.turboApi = turboApi;
    }

    public String deal(String input) {
        try {
            return turboApi.callWithMessage(input);
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            log.error("An error occurred while calling the generation service: " + e.getMessage());
        }
        return DEFAULT_RESPONSE;
    }
}
