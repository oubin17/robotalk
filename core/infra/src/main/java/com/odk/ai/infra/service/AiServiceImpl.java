package com.odk.ai.infra.service;

import com.odk.ai.baseutil.enums.AiProviderEnum;
import com.odk.ai.infra.api.AiService;
import com.odk.ai.infra.api.provider.AiProvider;
import com.odk.base.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AiService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final List<AiProvider> providers;

    @Override
    public String chat(AiProviderEnum aiProvider, String prompt) {
        return providers.stream()
                .filter(p -> p.getProviderName() == aiProvider)
                .findFirst()
                .orElseThrow(() -> new BizException("Ai Provider not found"))
                .chat(prompt);
    }
}
