package com.odk.ai.infra.api;

import com.odk.ai.baseutil.enums.AiProviderEnum;

/**
 * AiService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
public interface AiService {


    /**
     * 选择 AI 服务
     *
     * @param aiProvider
     * @param prompt
     * @return
     */
    String chat(AiProviderEnum aiProvider, String prompt);
}
