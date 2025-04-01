package com.odk.ai.infra.service.provider;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.odk.ai.baseutil.enums.AiProviderEnum;
import com.odk.ai.infra.api.provider.AiProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * DeepSeekProvider
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "ai.providers.deepseek", name = "enabled", havingValue = "true")
public class DeepSeekProvider implements AiProvider {

    @Value("${ai.providers.deepseek.api-key}")
    private String apiKey;

    @Override
    public AiProviderEnum getProviderName() {
        return AiProviderEnum.DEEPSEEK;
    }


    @Override
    public String chat(String prompt) {
        Generation gen = new Generation();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(prompt)
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(apiKey)
                .model(AiProviderEnum.DEEPSEEK.getCode())
                .messages(Arrays.asList(userMsg))
                // 不可以设置为"text"
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        try {
            GenerationResult call = gen.call(param);
            log.info("思考过程：{}", call.getOutput().getChoices().get(0).getMessage().getReasoningContent());
            log.info("回复内容：{}", call.getOutput().getChoices().get(0).getMessage().getContent());
            return call.getOutput().getChoices().get(0).getMessage().getContent();

        }  catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            log.error("An error occurred while calling the generation service: " + e.getMessage());
        }
        return DEFAULT_RESPONSE;

    }
}
