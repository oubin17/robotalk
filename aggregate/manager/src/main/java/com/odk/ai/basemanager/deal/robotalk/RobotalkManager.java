package com.odk.ai.basemanager.deal.robotalk;

import com.odk.ai.baseutil.enums.AiProviderEnum;
import com.odk.ai.infra.api.AiService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RobotalkManager {

    private final AiService aiService;

    public String deal(String input, String llm) {
        return aiService.chat(AiProviderEnum.getByCode(llm), input);
    }
}
