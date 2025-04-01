package com.odk.ai.infra.api.provider;

import com.odk.ai.baseutil.enums.AiProviderEnum;

/**
 * AiProvider
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
public interface AiProvider {

    /**
     * 模型名称，如 "openai", "qwen"
     *
     * @return
     */
    AiProviderEnum getProviderName();

    /**
     * 同步调用
     *
     * @param prompt
     * @return
     */
    String chat(String prompt);

    String DEFAULT_RESPONSE = "对不起，暂时无法提供服务...";
}
