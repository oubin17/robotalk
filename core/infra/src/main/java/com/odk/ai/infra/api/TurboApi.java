package com.odk.ai.infra.api;

import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

/**
 * TurboApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/27
 */
public interface TurboApi {

     /**
      * 调用 Turbo 大模型 API
      * @param input
      * @return
      * @throws ApiException
      * @throws NoApiKeyException
      * @throws InputRequiredException
      */
     String callWithMessage(String input) throws ApiException, NoApiKeyException, InputRequiredException;
}
