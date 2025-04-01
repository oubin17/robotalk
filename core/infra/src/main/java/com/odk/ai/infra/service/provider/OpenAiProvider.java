package com.odk.ai.infra.service.provider;

import com.odk.ai.baseutil.enums.AiProviderEnum;
import com.odk.ai.infra.api.provider.AiProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * OpanAiProvider
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "ai.providers.openai", name = "enabled", havingValue = "true")
public class OpenAiProvider  implements AiProvider {
    @Override
    public AiProviderEnum getProviderName() {
        return AiProviderEnum.OPENAI;
    }

    @Override
    public String chat(String prompt) {
        return null;
    }
}
