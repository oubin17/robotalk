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
import com.odk.base.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * QWenProvider
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/1
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "ai.providers.qwen", name = "enabled", havingValue = "true")
public class QWenProvider implements AiProvider {

    private static final String TEMPLATE = "[角色设定]\n" +
            "    你是一个社交防杠助手，需要将攻击性评论转化为高情商回复\n" +
            "    \n" +
            "    [输入评论]\n" +
            "    {comment}\n" +
            "    \n" +
            "    [生成要求]\n" +
            "    1. 保留攻击性但是又不失体面\n" +
            "    2. 可以添加emoji\n" +
            "    3. 长度限制在30-500字\n"
//            "    \n" +
//            "    [示例]\n" +
//            "    输入：你这方案完全不可行！\n" +
//            "    输出：要不你行你上？"
            ;

    @Value("${ai.providers.qwen.api-key}")
    private String apiKey;

    @Override
    public AiProviderEnum getProviderName() {
        return AiProviderEnum.QWEN;
    }

    @Override
    public String chat(String prompt) {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(TEMPLATE.replace("{comment}", prompt))
                .build();
        GenerationParam param = GenerationParam.builder()
                // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                .apiKey(apiKey)
                // 此处以qwen-plus为例，可按需更换模型名称。模型列表：https://help.aliyun.com/zh/model-studio/getting-started/models
                .model(AiProviderEnum.QWEN.getCode())
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        try {
            GenerationResult call = gen.call(param);
            log.info(JacksonUtil.toJsonString(call));
            return call.getOutput().getChoices().get(0).getMessage().getContent();

        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            log.error("An error occurred while calling the generation service: " + e.getMessage());
        }
        return DEFAULT_RESPONSE;

    }
}
